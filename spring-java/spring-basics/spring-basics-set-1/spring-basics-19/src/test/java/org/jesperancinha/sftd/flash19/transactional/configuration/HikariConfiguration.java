package org.jesperancinha.sftd.flash19.transactional.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

import static org.jesperancinha.sftd.flash19.transactional.containers.AbstractTestContainersIT.postgreSQLContainer;

@Configuration
public class HikariConfiguration {
    @Bean
    @Primary
    DataSource dataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(postgreSQLContainer.getJdbcUrl());
        hikariConfig.setUsername(postgreSQLContainer.getUsername());
        hikariConfig.setPassword(postgreSQLContainer.getPassword());
        return new HikariDataSource(hikariConfig);
    }
}
