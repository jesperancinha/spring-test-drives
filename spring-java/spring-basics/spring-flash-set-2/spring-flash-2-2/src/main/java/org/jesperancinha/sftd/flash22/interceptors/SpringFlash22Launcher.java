package org.jesperancinha.sftd.flash22.interceptors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SpringFlash22Launcher {
    public static void main(String[] args) {
        SpringApplication.run(SpringFlash22Launcher.class, args);
    }

    @GetMapping("/")
    public String getString() {
        return "Fine Wine";
    }
}
