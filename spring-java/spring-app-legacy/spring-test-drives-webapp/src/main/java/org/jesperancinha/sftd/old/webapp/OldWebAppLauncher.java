package org.jesperancinha.sftd.old.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableCaching
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "org.jesperancinha.sftd.old.webapp.repository")
@SpringBootApplication
public class OldWebAppLauncher {
    public static void main(String[] args) {
        SpringApplication.run(OldWebAppLauncher.class, args).refresh();
    }
}
