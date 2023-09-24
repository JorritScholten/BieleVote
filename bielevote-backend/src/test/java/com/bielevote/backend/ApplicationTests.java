package com.bielevote.backend;

import com.bielevote.backend.project.ProjectRepository;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

@SpringBootTest
class ApplicationTests {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private DataSource dataSource;

    @Test
    @Order(1)
    void contextLoads() {
    }

    @Test
    @EnabledIfEnvironmentVariable(named = "DUMP2SQL", matches = "TRUE")
    void dumpDatabase() {
        new DevelopmentController().dumpDbToSQL();
    }
}
