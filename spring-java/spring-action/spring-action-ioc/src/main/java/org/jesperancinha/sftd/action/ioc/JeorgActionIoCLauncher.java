package org.jesperancinha.sftd.action.ioc;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.jesperancinha.sftd.action.ioc.interfaces.RestaurantInterface;
import org.jesperancinha.sftd.action.ioc.model.Cutlery;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.jesperancinha.console.consolerizer.console.ConsolerizerComposer.title;

@SpringBootApplication
public class JeorgActionIoCLauncher implements ApplicationRunner {

    @Value("La tagliatella")
    public String restaurante;

    private final Cutlery cutlery;

    public JeorgActionIoCLauncher(@Qualifier("cutlery") Cutlery cutlery) {
        this.cutlery = cutlery;
    }

    public static void main(String[] args) {
        SpringApplication.run(JeorgActionIoCLauncher.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        RestaurantInterface.tutorialOne();
        RestaurantInterface.tutorialTwo();
        final RestaurantInterface.Table table = new RestaurantInterface.Table();
        ConsolerizerComposer.outSpace()
                .blue("We've create table %s", table)
                .reset();

        ConsolerizerComposer.outSpace()
                .magenta("We were able to create:")
                .magenta("1. Nested types")
                .magenta("2. Static methods")
                .reset();

        ConsolerizerComposer.outSpace()
                .orange("Presenting the cutlery")
                .yellow(cutlery)
                .reset();

        ConsolerizerComposer.outSpace()
                .cyan(title("Our restaurante name is:"))
                .cyan(restaurante)
                .reset();
    }
}
