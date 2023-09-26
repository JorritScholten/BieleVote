package com.bielevote.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@Configuration
public class PopulateDatabase {
    static public final String db_dump_name = "db_dump.sql";
    static public final ClassPathResource dbContents = new ClassPathResource("/data/" + db_dump_name);

    @Autowired
    @Bean
    public DataSourceInitializer dataSourceInitializer(DataSource dataSource) {
        var resourceDatabasePopulator = new ResourceDatabasePopulator();
        resourceDatabasePopulator.addScript(dbContents);
        var dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(dataSource);
        dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
        return dataSourceInitializer;
    }
}
