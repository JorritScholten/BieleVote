package com.bielevote.backend;

import com.bielevote.backend.config.PopulateDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.SQLException;

@RestController
@RequestMapping("/dev")
public class DevelopmentController {
    public static final String dataResourcesDir = "src/main/resources/data/";
    public static final String[] tableInsertOrder = new String[]{
            "USERS", "NEWS_ARTICLES", "PROJECTS", "REWARDS",
            "TRANSACTIONS", "VOTES", "ACCOUNT_REQUESTS"};
    @Autowired
    private DataSource dataSource;

    @GetMapping("/kill")
    public void shutdownApplication() {
        System.out.println("Shutting down bielevote-backend...");
        System.exit(0);
    }

    @GetMapping("/save_db")
    public ResponseEntity<String> dumpDbToSQL() {
        try {
            var dumpFile = Path.of(dataResourcesDir + PopulateDatabase.db_dump_name).toFile();
            try (var file = new FileOutputStream(dumpFile);
                 var connection = dataSource.getConnection()) {
                var statement = connection.createStatement();
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
                    if (count != 0) {
                        count++;
                        file.write(("ALTER SEQUENCE \"PUBLIC\".\"" + table + "_SEQ\" RESTART WITH " + count + ";\n")
                                .getBytes()
                        );
                    }
                    file.write('\n');
                }
                file.write('\n');
                return ResponseEntity.ok("Successfully serialized database to .sql file.");
            } catch (FileNotFoundException e) {
                if (!new File(dumpFile.getPath()).exists()) {
                    return new ResponseEntity<>(
                            "Can't access path: " + dumpFile.getPath() + "\nDoes this folder exist?",
                            HttpStatus.NOT_FOUND
                    );
                } else {
                    return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        } catch (IOException | SQLException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
