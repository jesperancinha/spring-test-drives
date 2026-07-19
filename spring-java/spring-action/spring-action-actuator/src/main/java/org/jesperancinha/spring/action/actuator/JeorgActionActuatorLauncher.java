package org.jesperancinha.spring.action.actuator;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.health.application.DiskSpaceHealthIndicator;
import org.springframework.boot.health.contributor.PingHealthIndicator;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.jesperancinha.console.consolerizer.console.ConsolerizerComposer.title;

@SpringBootApplication
public class JeorgActionActuatorLauncher implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(JeorgActionActuatorLauncher.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {

        ConsolerizerComposer.outSpace()
                .cyan(title("Health Indicators Available by default with no extra technology-specific starter"))
                .magenta(DiskSpaceHealthIndicator.class)
                .magenta(PingHealthIndicator.class)
                .reset();

    }
}
