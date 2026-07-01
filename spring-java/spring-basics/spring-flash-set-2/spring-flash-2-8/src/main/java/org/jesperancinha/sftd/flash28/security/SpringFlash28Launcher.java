package org.jesperancinha.sftd.flash28.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SpringFlash28Launcher {
    public static void main(String[] args) {
        SpringApplication.run(SpringFlash28Launcher.class, args);
    }

    @GetMapping("/")
    public String getBird() {
        return "A little bird lit down on henry lee";
    }
}
