package org.jesperancinha.sftd.flash514.packages;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.jesperancinha.sftd.flash514.packages.abandoned.Leeks;
import org.jesperancinha.sftd.flash514.packages.components.Chives;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "org.jesperancinha.sftd.flash514.packages.components")
public class SpringFlash514Launcher implements ApplicationRunner {

    @Autowired(required = false)
    private Leeks leeks;

    @Autowired(required = false)
    private Chives chives;

    public static void main(String[] args) {
        SpringApplication.run(SpringFlash514Launcher.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ConsolerizerComposer.outSpace()
                .blue("We have chives -> %s", chives)
                .blue("But we don't have leeks -> %s", leeks)
                .reset();

    }
}
