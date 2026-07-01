package org.jesperancinha.sftd.flash10.error;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.YELLOW;

@RestController
@SpringBootApplication
public class SpringFlash10Launcher {
    public static void main(String[] args) {
        SpringApplication.run(SpringFlash10Launcher.class, args);
    }

    @GetMapping("/")
    public String getString() {
        YELLOW.printGenericTitleLn("You just reached a method that throws an exception");
        throw new RuntimeException();
    }
}
