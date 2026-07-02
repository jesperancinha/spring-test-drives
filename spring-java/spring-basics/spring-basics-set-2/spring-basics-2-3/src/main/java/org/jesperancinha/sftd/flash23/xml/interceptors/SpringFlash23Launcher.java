package org.jesperancinha.sftd.flash23.xml.interceptors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringFlash23Launcher {
    public static void main(String[] args) {
        SpringApplication.run(SpringFlash23Launcher.class, args);
    }

    @GetMapping("/")
    public String getString() {
        return "Fine Wine";
    }
}
