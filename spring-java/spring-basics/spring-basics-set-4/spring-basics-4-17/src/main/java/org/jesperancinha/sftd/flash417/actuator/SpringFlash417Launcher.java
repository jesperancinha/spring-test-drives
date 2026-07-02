package org.jesperancinha.sftd.flash417.actuator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SpringFlash417Launcher {
    public static void main(String[] args) {
        SpringApplication.run(SpringFlash417Launcher.class, args);
    }

    @GetMapping("/")
    public String getCity() {
        return "Ghost Town";
    }
}