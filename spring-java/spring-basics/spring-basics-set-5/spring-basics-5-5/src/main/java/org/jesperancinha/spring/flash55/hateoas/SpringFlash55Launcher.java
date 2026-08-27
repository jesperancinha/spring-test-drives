package org.jesperancinha.spring.flash55.hateoas;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.GREEN;

@SpringBootApplication
public class SpringFlash55Launcher implements CommandLineRunner {

    public static void main(String[] args) {
        GREEN.printGenericTitleLn("Service is starting");
        SpringApplication.run(SpringFlash55Launcher.class, args);
    }

    @Override
    public void run(String... args) {

    }
}
