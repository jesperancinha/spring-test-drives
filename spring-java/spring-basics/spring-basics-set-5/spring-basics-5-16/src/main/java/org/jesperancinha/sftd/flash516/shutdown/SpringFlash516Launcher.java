package org.jesperancinha.sftd.flash516.shutdown;

import org.jesperancinha.sftd.flash516.shutdown.controller.SkynetNetwork;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringFlash516Launcher implements ApplicationRunner {

    private final SkynetNetwork skynetNetwork;

    public SpringFlash516Launcher(SkynetNetwork skynetNetwork) {
        this.skynetNetwork = skynetNetwork;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringFlash516Launcher.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

    }
}
