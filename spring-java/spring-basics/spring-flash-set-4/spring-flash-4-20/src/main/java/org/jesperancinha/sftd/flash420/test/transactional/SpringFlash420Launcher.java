package org.jesperancinha.sftd.flash420.test.transactional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SpringFlash420Launcher {
    public static void main(String[] args) {
        SpringApplication.run(SpringFlash420Launcher.class, args);
    }
}