package com.bielevote.backend;

import com.bielevote.backend.config.PopulateDatabase;
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
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;

@SpringBootTest
class ApplicationTests {
    final String seedValuesDir = "src/test/java/com/bielevote/backend/seed_values/";
    final String dataResourcesDir = "src/main/resources/data/";
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
    void dumpDatabase() throws SQLException, IOException {
        var dumpFile = Path.of(dataResourcesDir + PopulateDatabase.db_dump_name).toFile();
//        var dumpFile = PopulateDatabase.dbContents.getFile();
        try (var file = new FileOutputStream(dumpFile);
             var connection = dataSource.getConnection()) {
            var statement = connection.createStatement();
            final String[] tableInsertOrder = new String[]{"USERS", "NEWS_ARTICLE", "PROJECT", "REWARD", "TRANSACTIONS", "VOTES"};
            for (var table : tableInsertOrder) {
                int count = 0;
                file.write(("-- Entries for " + table + "\n").getBytes());
                statement.execute("SCRIPT SIMPLE COLUMNS NOSETTINGS TABLE " + table);
                for (var dumpedDatabase = statement.getResultSet(); dumpedDatabase.next(); ) {
                    if (dumpedDatabase.getString(1).startsWith("INSERT INTO \"PUBLIC\".\"" + table)) {
                        file.write(dumpedDatabase.getBytes(1));
                        file.write('\n');
                        count++;
                    }
                }
                if(count!= 0){
                    count++;
                    file.write(("ALTER SEQUENCE \"PUBLIC\".\"" + table + "_SEQ\" RESTART WITH " + count + ";\n").getBytes());
                }
                file.write('\n');
            }
            file.write('\n');
        }
    }
}
