package org.jesperancinha.sftd.flash51.stereotypes;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.GREEN;

@SpringBootApplication
@EnableWebMvc
public class SpringFlash51Launcher implements CommandLineRunner {
    public static void main(String[] args) {
        GREEN.printGenericTitleLn("Service is starting");
        SpringApplication.run(SpringFlash51Launcher.class, args);
    }

    @Override
    public void run(String... args) {
        ConsolerizerComposer.outSpace()
                .magenta("The @Indexed annotation is the only stereotype annotation that does not mark a component to get picked via component scan")
                .reset();
        new BlurIndexedDreaming();
    }
}