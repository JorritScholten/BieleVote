package com.bielevote.backend.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {
    @Autowired
    ProjectRepository projectRepository;

    @GetMapping
    public List<Project> getProjects() {
        return projectRepository.findAll();
    }

    @PostMapping
    public Project postProject(@RequestBody Project project) {
        return projectRepository.save(project);
    }

}
