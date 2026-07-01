package org.jesperancinha.sftd.flash19.transactional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class SpringFlash19Launcher {
    public static void main(String[] args) {
        SpringApplication.run(SpringFlash19Launcher.class, args);
    }
}
