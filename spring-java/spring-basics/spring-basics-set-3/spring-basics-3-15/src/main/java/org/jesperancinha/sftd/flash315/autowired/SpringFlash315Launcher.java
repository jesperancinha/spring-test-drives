package org.jesperancinha.sftd.flash315.autowired;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.jesperancinha.sftd.flash315.autowired.domain.Something;
import org.jesperancinha.sftd.flash315.autowired.domain.SomethingElse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.BRIGHT_BLUE;

@SpringBootApplication
public class SpringFlash315Launcher implements CommandLineRunner {

    @Autowired(required = false)
    private Something something;

    @Autowired(required = false)
    private SomethingElse somethingElse;

    public static void main(String[] args) {
        SpringApplication.run(SpringFlash315Launcher.class, args);
    }

    @Override
    public void run(String... args) {
        BRIGHT_BLUE.printGenericLn("If you reached this and something is not null, it means that you are running profile test. Congratulations!");
        BRIGHT_BLUE.printGenericLn(something);

        ConsolerizerComposer.outSpace()
                .orange("SomethingElse gets initialized when test is active and pro is also active.")
                .orange("If SomethingElse is null, then it means the prod profile is probably not activated. Please check.")
                .green("This is what you get for your current configuration")
                .magenta(somethingElse)
                .reset();

    }
}