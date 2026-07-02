package org.jesperancinha.sftd.flash58.conditionals;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Endpoint(id = "greatEndpointId")
@SpringBootApplication
public class SpringFlash58Launcher implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(SpringFlash58Launcher.class, args);
    }

    @Override
    public void run(String... args) {

    }
}
