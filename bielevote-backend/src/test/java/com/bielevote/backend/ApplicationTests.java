package com.bielevote.backend;

import com.bielevote.backend.project.ProjectRepository;
import com.bielevote.backend.project.ProjectViews;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@SpringBootTest
class ApplicationTests {
    final String seedValuesDir = "src/test/java/com/bielevote/backend/seed_values/";
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private DataSource dataSource;

    @Test
    @Order(1)
    void contextLoads() {
    }

    @Test
    @EnabledIfEnvironmentVariable(named = "SERIALIZE", matches = "TRUE")
    void serializeProjects() {
        try {
            final var projectFile = Path.of(seedValuesDir + "projects.json");
            System.out.println("path: " + projectFile.toAbsolutePath());
            var projects = projectRepository.findAll();
            var serializedProjects = JsonMapper.builder()
                    .disable(MapperFeature.DEFAULT_VIEW_INCLUSION)
                    .addModule(new JavaTimeModule())
                    .build()
                    .writerWithView(ProjectViews.Serialize.class)
                    .withDefaultPrettyPrinter()
                    .writeValueAsBytes(projects);
            Files.write(projectFile, serializedProjects);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @EnabledIfEnvironmentVariable(named = "DUMP2SQL", matches = "TRUE")
    void dumpDatabase() {
        new DevelopmentController().dumpDbToSQL();
    }
}
