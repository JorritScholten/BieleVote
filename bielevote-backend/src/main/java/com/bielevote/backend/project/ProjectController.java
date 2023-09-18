package com.bielevote.backend.project;

import com.bielevote.backend.news.NewsArticle;
import com.bielevote.backend.news.NewsArticleController;
import com.bielevote.backend.user.UserViews;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Project> postProject(@RequestBody Project project) {
        try {
            if (project.getDatePublished() == null) {
                project.setDatePublished(LocalDateTime.now());
            }
            project.setStatus(ProjectStatus.PROPOSED);
            return new ResponseEntity<>(projectRepository.save(project), HttpStatus.OK);
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
