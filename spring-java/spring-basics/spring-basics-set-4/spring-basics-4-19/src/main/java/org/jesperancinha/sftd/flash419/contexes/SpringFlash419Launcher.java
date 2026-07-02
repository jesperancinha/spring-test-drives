package org.jesperancinha.sftd.flash419.contexes;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SpringFlash419Launcher implements CommandLineRunner {

    private final SongObject object;

    public SpringFlash419Launcher(SongObject object) {
        this.object = object;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringFlash419Launcher.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}