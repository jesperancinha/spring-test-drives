package org.jesperancinha.sftd.flash414.boot.application;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.BRIGHT_BLUE;
import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.MAGENTA;

@SpringBootApplication
public class SpringFlash414Launcher implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(SpringFlash414Launcher.class, args);
    }

    @Override
    public void run(String... args) {
        Arrays.stream(this.getClass().getAnnotations()).forEach(annotation ->
        {
            BRIGHT_BLUE.printGenericLn(annotation);
            Arrays.stream(annotation.annotationType().getAnnotations())
                    .forEach(MAGENTA::printGenericLn);
        });
    }
}