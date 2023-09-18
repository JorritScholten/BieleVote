package com.bielevote.backend.project;

import com.bielevote.backend.user.UserViews;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/projects")
public class ProjectController {
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    private UserService userService;

    @JsonView(UserViews.getProject.class)
    @GetMapping()
    public ResponseEntity<Map<String, Object>> getAllProjects(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        try {
            List<Project> projects;
            PageRequest paging = PageRequest.of(page, size, Sort.by("datePublished").descending());

            Page<Project> pageProject = projectRepository.findAll(paging);

            projects = pageProject.getContent();

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

    @JsonView(UserViews.getProject.class)
    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable("id") long id) {
        try {
            return new ResponseEntity<>(projectRepository.findById(id).orElseThrow(), HttpStatus.OK);
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
}
