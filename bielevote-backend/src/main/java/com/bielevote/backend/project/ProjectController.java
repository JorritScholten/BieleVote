package com.bielevote.backend.project;

import com.bielevote.backend.user.UserService;
import com.bielevote.backend.votes.VoteType;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

//import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/projects")
public class ProjectController {
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    private UserService userService;

    @JsonView(ProjectViews.GetProjectList.class)
    @GetMapping()
    public ResponseEntity<Map<String, Object>> getAllProjects(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        try {
            var paging = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "datePublished"));
            var allowedPublicTypes = Set.of(ProjectStatus.ACTIVE, ProjectStatus.OLD, ProjectStatus.ACCEPTED, ProjectStatus.REJECTED);
            Page<Project> pageProject = projectRepository.findByStatusIn(allowedPublicTypes, paging);
            List<Project> projects = pageProject.getContent();

            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("projects", projects);
            responseBody.put("currentPage", pageProject.getNumber());
            responseBody.put("totalItems", pageProject.getTotalElements());
            responseBody.put("totalPages", pageProject.getTotalPages());

            return new ResponseEntity<>(responseBody, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectInfoDTO> getProjectById(@PathVariable("id") long id) {
        try {
            var project = projectRepository.findById(id).orElseThrow();
            var dto = new ProjectInfoDTO(
                    project.getTitle(),
                    project.getSummary(),
                    project.getContent(),
                    project.getAuthor().getLegalName(),
                    project.getDatePublished(),
                    project.getStatus(),
                    project.getVotes().stream().filter(v -> v.getType() == VoteType.POSITIVE).count(),
                    project.getVotes().stream().filter(v -> v.getType() == VoteType.NEUTRAL).count(),
                    project.getVotes().stream().filter(v -> v.getType() == VoteType.AGAINST).count()
            );
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Project> postProject(@Validated @RequestBody Project project) {
        try {
            var auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth == null) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            project.setAuthor(userService.getByUsername(auth.getName()).orElseThrow());
            project.setDatePublished(LocalDateTime.now());
            if (project.getStatus() == null) {
                project.setStatus(ProjectStatus.PROPOSED);
            } else if (!(project.getStatus() == ProjectStatus.PROPOSED || project.getStatus() == ProjectStatus.EDITING)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(projectRepository.save(project), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Project> deleteProjectById(@PathVariable("id") long id) {
        try {
            if (projectRepository.findById(id).isEmpty()) {
                throw new RuntimeException();
            }
            projectRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    record ProjectInfoDTO(String title, String summary, String content, String author, LocalDateTime datePublished,
                          ProjectStatus status, Long votesFor, Long votesNeutral, Long votesAgainst) {
    }
}
