package org.jesperancinha.sftd.flash41.scope.prototype;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.GREEN;

@SpringBootApplication
public class SpringFlash41Launcher {
    public static void main(String[] args) {
        GREEN.printGenericTitleLn("Service is starting");
        SpringApplication.run(SpringFlash41Launcher.class, args);
    }
}