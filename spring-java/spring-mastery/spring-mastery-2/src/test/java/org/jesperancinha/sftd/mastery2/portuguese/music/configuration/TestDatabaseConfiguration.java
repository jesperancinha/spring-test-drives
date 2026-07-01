package org.jesperancinha.sftd.mastery2.portuguese.music.configuration;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

@TestConfiguration
public class TestDatabaseConfiguration {
    @Bean
    public DatabasePopulator databasePopulator() {
        final ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
        resourceDatabasePopulator.setContinueOnError(false);
        resourceDatabasePopulator.addScript(new ClassPathResource("artists_batch1.sql"));
        return resourceDatabasePopulator;
    }
}

