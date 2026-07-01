package org.jesperancinha.sftd.flash33.rollback.transactional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class SpringFlash33Launcher {
    public static void main(String[] args) {
        SpringApplication.run(SpringFlash33Launcher.class, args);
    }
}
