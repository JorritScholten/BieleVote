package com.bielevote.backend.project;

import com.bielevote.backend.user.User;
import com.bielevote.backend.user.UserRole;
import com.bielevote.backend.user.UserService;
import com.bielevote.backend.user.rewardpoint.Transaction;
import com.bielevote.backend.user.rewardpoint.TransactionReason;
import com.bielevote.backend.user.rewardpoint.TransactionRepository;
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
import java.util.function.Function;
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
            ProjectStatus.DENIED,
            ProjectStatus.REVIEW
    );
    @Value("${app.proposal-rules.max-per-month}")
    int maxProjectsPerMonth;
    @Value("${app.proposal-rules.minimum-votes}")
    int minimumRequiredVotes;
    @Value("${app.reward-rules.amount-for-accepted}")
    int rewardForAccepted;
    @Autowired
    private ProjectRepository projectRepository;
    Function<User, Boolean> hasNotExceededMaxPerMonth = user ->
            projectRepository.countByAuthorAndDatePublishedAfter(user, LocalDateTime.now().minusMonths(1)) < maxProjectsPerMonth;
    @Autowired
    private UserService userService;
    @Autowired
    private VoteRepository voteRepository;
    Function<User, Boolean> hasVotedEnough = user -> voteRepository.countByUser(user) >= minimumRequiredVotes;
    @Autowired
    private TransactionRepository transactionRepository;

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
                    percentage,
                    project.getEndOfVoting());
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
        if (!checkIfAllowedToPropose(currentUser)) {
            return new ResponseEntity<>(HttpStatus.TOO_MANY_REQUESTS);
        }
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
        return switch (user.getRole()) {
            case MUNICIPAL -> ResponseEntity.ok(true);
            case CITIZEN -> ResponseEntity.ok(checkIfAllowedToPropose(user));
            default -> ResponseEntity.ok(false);
        };
    }

    @GetMapping("/allowed_to_post/reasons")
    public ResponseEntity<List<String>> reasonsDeniedProposal(@AuthenticationPrincipal User user) {
        if (user.getRole().equals(UserRole.ADMINISTRATOR)) {
            return ResponseEntity.ok(List.of("Account type not allowed to propose new projects."));
        } else if (user.getRole().equals(UserRole.CITIZEN)) {
            List<String> reasons = new ArrayList<>();
            if (!hasVotedEnough.apply(user)) {
                reasons.add("User hasn't voted enough times (currently: %d), minimum needed: %d."
                        .formatted(voteRepository.countByUser(user), minimumRequiredVotes));
            }
            if (!hasNotExceededMaxPerMonth.apply(user)) {
                var projectsFromThisMonth = projectRepository.findByAuthorAndDatePublishedAfter(user,
                        LocalDateTime.now().minusMonths(1));
                projectsFromThisMonth.sort(Comparator.comparing(Project::getDatePublished));
                var earliestProjectThisMonth = projectsFromThisMonth.get(0);
                reasons.add(("User has met their monthly posting limit, a maximum of %d new projects per month are" +
                        " allowed. Allowed to post again after %s.")
                        .formatted(maxProjectsPerMonth, earliestProjectThisMonth.getDatePublished().plusMonths(1)));
            }
            return ResponseEntity.ok(reasons);
        } else {
            return ResponseEntity.ok(List.of());
        }
    }

    boolean checkIfAllowedToPropose(User user) {
        return user.getRole().equals(UserRole.MUNICIPAL) || (user.getRole().equals(UserRole.CITIZEN)
                && hasVotedEnough.apply(user) && hasNotExceededMaxPerMonth.apply(user));
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
            var project = projectRepository.findById(id).orElseThrow();
            if (project.getStatus().equals(ProjectStatus.PROPOSED)) {
                if (newStatus != ProjectStatus.ACTIVE && newStatus != ProjectStatus.DENIED) {
                    throw new IllegalArgumentException();
                }
            } else if (project.getStatus().equals(ProjectStatus.REVIEW)) {
                if (newStatus != ProjectStatus.ACCEPTED && newStatus != ProjectStatus.REJECTED) {
                    throw new IllegalArgumentException();
                }
            } else {
                throw new IllegalArgumentException();
            }

            project.setStatus(newStatus);
            if (newStatus == ProjectStatus.ACTIVE) {
                project.setStartOfVoting(LocalDateTime.now());
                project.setEndOfVoting(LocalDateTime.now().plus(Period.ofDays(daysVotingActive)));
            } else if (newStatus == ProjectStatus.ACCEPTED && project.getAuthor().getRole().equals(UserRole.CITIZEN)) {
                transactionRepository.saveAndFlush(Transaction.builder()
                        .amount(rewardForAccepted)
                        .date(LocalDateTime.now())
                        .user(project.getAuthor())
                        .reason(TransactionReason.PROJECT_ACCEPTED)
                        .build()
                );
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
                                 Long votesAgainst, Long progressPercentage, LocalDateTime endOfVoting) {
    }

    public record ProjectDTO(String title, String summary, String content, ProjectStatus status) {
    }
}
