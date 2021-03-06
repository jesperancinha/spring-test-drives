package org.jesperancinha.std.flash210.factory;

import org.jesperancinha.console.consolerizer.console.Consolerizer;
import org.jesperancinha.std.flash210.factory.configurations.Material;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringFlash210Launcher implements CommandLineRunner {

    private final Material material;

    public SpringFlash210Launcher(Material material) {
        this.material = material;
    }


    public static void main(String[] args) {
        SpringApplication.run(SpringFlash210Launcher.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Consolerizer.printRainbowTitleLn(material);

    }
}
