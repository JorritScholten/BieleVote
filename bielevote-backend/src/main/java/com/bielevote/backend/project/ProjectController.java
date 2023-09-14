package com.bielevote.backend.project;

import com.bielevote.backend.user.UserViews;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/project")
public class ProjectController {
    @Autowired
    ProjectRepository projectRepository;

    @JsonView(UserViews.getProject.class)
    @GetMapping
    public ResponseEntity<List<Project>> getProjects() {
        return ResponseEntity.ok(projectRepository.findAll());
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
