package com.bielevote.backend.project;

import com.bielevote.backend.user.User;
import com.bielevote.backend.user.UserRole;
import com.bielevote.backend.user.UserService;
import com.bielevote.backend.votes.VoteRepository;
import com.bielevote.backend.votes.VoteType;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/projects")
public class ProjectController {
    private static final Set<ProjectStatus> allowedPublicTypes = Set.of(
            ProjectStatus.ACTIVE,
            ProjectStatus.ACCEPTED,
            ProjectStatus.REJECTED
    );
    private static final Set<ProjectStatus> allowedMunicipalTypes = Set.of(
            ProjectStatus.ACTIVE,
            ProjectStatus.ACCEPTED,
            ProjectStatus.REJECTED,
            ProjectStatus.PROPOSED,
            ProjectStatus.DENIED
    );
    @Value("${app.proposal-rules.max-per-month}")
    int maxProjectsPerMonth;
    @Value("${app.proposal-rules.minimum-votes}")
    int minimumRequiredVotes;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private VoteRepository voteRepository;

    @JsonView(ProjectViews.GetProjectList.class)
    @GetMapping()
    public ResponseEntity<Map<String, Object>> getAllProjects(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) List<String> statusList,
            @AuthenticationPrincipal User currentUser
    ) {
        try {
            var paging = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "datePublished"));
            final Set<ProjectStatus> statusFilter = new HashSet<>(statusList == null ? allowedPublicTypes :
                    statusList.stream().map(ProjectStatus::valueOf).collect(Collectors.toSet()));
            if (currentUser == null) {
                statusFilter.retainAll(allowedPublicTypes);
            } else if (currentUser.getRole() == UserRole.MUNICIPAL) {
                statusFilter.retainAll(allowedMunicipalTypes);
            } else { // this is duplicate for now, will probably add filter for own projects
                statusFilter.retainAll(allowedPublicTypes);
            }
            Page<Project> pageProject = projectRepository.findByStatusIn(statusFilter, paging);

            List<Project> projects = pageProject.getContent();
            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("projects", projects);
            responseBody.put("currentPage", pageProject.getNumber());
            responseBody.put("totalItems", pageProject.getTotalElements());
            responseBody.put("totalPages", pageProject.getTotalPages());

            return new ResponseEntity<>(responseBody, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectInfoDTO> getProjectById(@PathVariable("id") long id,
                                                         @AuthenticationPrincipal User currentUser) {
        try {
            var project = projectRepository.findById(id).orElseThrow();
            if (currentUser != null && currentUser.getRole() == UserRole.MUNICIPAL) {
                if (!allowedMunicipalTypes.contains(project.getStatus())) {
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
            } else {
                if (!allowedPublicTypes.contains(project.getStatus())) {
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
            }
            long percentage = 0;
            if (project.getStatus() == ProjectStatus.ACTIVE) {
                final long timePassed = Duration.between(project.getStartOfVoting(), LocalDateTime.now()).toSeconds();
                final long totalTime = Duration.between(project.getStartOfVoting(), project.getEndOfVoting()).toSeconds();
                percentage = (long) (((double) timePassed / (double) totalTime) * 100.0);
            }
            var dto = new ProjectInfoDTO(
                    project.getTitle(),
                    project.getSummary(),
                    project.getContent(),
                    project.getAuthor().getLegalName(),
                    project.getDatePublished(),
                    project.getStatus(),
                    project.getVotes().stream().filter(v -> v.getType() == VoteType.POSITIVE).count(),
                    project.getVotes().stream().filter(v -> v.getType() == VoteType.NEUTRAL).count(),
                    project.getVotes().stream().filter(v -> v.getType() == VoteType.AGAINST).count(),
                    percentage);
            return ResponseEntity.ok(dto);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @JsonView(ProjectViews.GetProjectList.class)
    @PostMapping
    public ResponseEntity<Project> postProject(@Validated @RequestBody ProjectDTO projectDTO,
                                               @AuthenticationPrincipal User currentUser) {
        try {
            var project = new Project();
            project.setStatus(projectDTO.status);
            project.setSummary(projectDTO.summary);
            project.setContent(projectDTO.content);
            project.setTitle(projectDTO.title);
            project.setAuthor(currentUser);
            project.setDatePublished(LocalDateTime.now());
            if (currentUser.getRole().equals(UserRole.MUNICIPAL)) {
                project.setStatus(ProjectStatus.ACTIVE);
            } else if (!(project.getAuthor().getRole().equals(UserRole.CITIZEN)
                    && (project.getStatus() == ProjectStatus.PROPOSED || project.getStatus() == ProjectStatus.EDITING))) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(projectRepository.save(project), HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/allowed_to_post")
    public ResponseEntity<Boolean> allowedToPropose(@AuthenticationPrincipal User user) {
        if (user.getRole().equals(UserRole.MUNICIPAL)) {
            return ResponseEntity.ok(true);
        } else if (user.getRole().equals(UserRole.CITIZEN)) {
            return ResponseEntity.ok(checkIfAllowedToPropose(user));
        } else {
            return ResponseEntity.ok(false);
        }
    }

    boolean checkIfAllowedToPropose(User user) {
        return voteRepository.countByUser(user) >= minimumRequiredVotes &&
                projectRepository.countByAuthorAndDatePublishedAfter(user, LocalDateTime.now().minusMonths(1)) < maxProjectsPerMonth;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Project> deleteProjectById(@PathVariable("id") long id) {
        try {
            if (projectRepository.findById(id).isEmpty()) {
                throw new RuntimeException();
            }
            projectRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @JsonView(ProjectViews.GetProjectList.class)
    @PatchMapping("/status/{id}")
    public ResponseEntity<Project> handleProposal(@PathVariable("id") long id,
                                                  @RequestHeader(value = "newStatus") String status,
                                                  @Value("${app.project-rules.days-voting-active}") final int daysVotingActive) {
        try {
            var newStatus = ProjectStatus.valueOf(status);
            if (newStatus != ProjectStatus.ACTIVE && newStatus != ProjectStatus.DENIED) {
                throw new IllegalArgumentException();
            }
            var project = projectRepository.findById(id).orElseThrow();
            if (project.getStatus() != ProjectStatus.PROPOSED) throw new IllegalArgumentException();
            project.setStatus(newStatus);
            if (newStatus == ProjectStatus.ACTIVE) {
                project.setStartOfVoting(LocalDateTime.now());
                project.setEndOfVoting(LocalDateTime.now().plus(Period.ofDays(daysVotingActive)));
            }
            return ResponseEntity.ok(projectRepository.save(project));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public record ProjectInfoDTO(String title, String summary, String content, String author,
                                 LocalDateTime datePublished, ProjectStatus status, Long votesFor, Long votesNeutral,
                                 Long votesAgainst, Long progressPercentage) {
    }

    public record ProjectDTO(String title, String summary, String content, ProjectStatus status) {
    }
}
