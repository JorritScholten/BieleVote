package com.bielevote.backend.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProjectSeeder implements CommandLineRunner {
    @Autowired
    ProjectRepository projectRepository;

    @Override
    public void run(String... args) throws Exception {
        seedProjects();
    }

    void seedProjects() {
        long count = projectRepository.count();
        if (count == 0) {
            List<Project> projects = List.of(
                    new Project("Park", "new park"),
                    new Project("Swimming pool", "new swimming pool")
            );
            projectRepository.saveAll(projects);
            count = projectRepository.count();
        }
        System.out.println(count + " items seeded");
    }
}
