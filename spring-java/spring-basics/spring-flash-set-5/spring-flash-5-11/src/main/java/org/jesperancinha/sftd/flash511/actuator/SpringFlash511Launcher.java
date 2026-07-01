package org.jesperancinha.sftd.flash511.actuator;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringFlash511Launcher implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(SpringFlash511Launcher.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        ConsolerizerComposer.outSpace()
                .bgRed("It is important to note that the sessions endpoint does not come out of the box in Spring")
                .green("It has to be manually implemented!");

        ConsolerizerComposer.outSpace()
                .none()
                .blue("http://localhost:8081/actuator/sessions").green("for sessions")
                .newLine()
                .blue("http://localhost:8081/actuator").green("for actuators")
                .newLine()
                .reset();
    }
}
