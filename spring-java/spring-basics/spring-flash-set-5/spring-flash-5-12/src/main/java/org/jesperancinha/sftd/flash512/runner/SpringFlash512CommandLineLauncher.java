package org.jesperancinha.sftd.flash512.runner;

import org.jesperancinha.console.consolerizer.common.ConsolerizerVars;
import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.jesperancinha.console.consolerizer.console.ConsolerizerComposer.title;

@SpringBootApplication
@Profile("command")
public class SpringFlash512CommandLineLauncher implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(SpringFlash512CommandLineLauncher.class, args);
    }

    @Override
    public void run(String... args) {
        ConsolerizerComposer.outSpace()
                .cyan(title("CommandLineRunner"))
                .outList("We've started our application with non option args %s %s %s",
                        ConsolerizerVars.create(args))
                .blue()
                .outList("We've started our application with non option args %s",
                        ConsolerizerVars.createListFromStrings(Arrays.stream(args).collect(Collectors.toList())))
                .unicorns(100)
                .bgOrange("And this is how start")
                .reset();
    }
}
