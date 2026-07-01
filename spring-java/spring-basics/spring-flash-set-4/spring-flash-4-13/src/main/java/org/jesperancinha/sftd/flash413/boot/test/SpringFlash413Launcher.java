package org.jesperancinha.sftd.flash413.boot.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringFlash413Launcher {
    public static void main(String[] args) {
        SpringApplication.run(new Class[]{SpringFlash413Launcher.class}, args);
    }
}