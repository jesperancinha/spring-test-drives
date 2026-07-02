package org.jesperancinha.sftd.flash512.runner;

import org.jesperancinha.console.consolerizer.common.ConsolerizerVars;
import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.jesperancinha.console.consolerizer.console.ConsolerizerComposer.title;

@SpringBootApplication
@Profile("application")
public class SpringFlash512ApplicationLauncher implements ApplicationRunner {
    public static void main(String[] args) {
        SpringApplication.run(SpringFlash512ApplicationLauncher.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        final List<Object[]> collect = args.getOptionNames().stream().map(option ->
                new Object[]{option, args.getOptionValues(option)})
                .collect(Collectors.toList());
        ConsolerizerComposer.outSpace()
                .cyan(title("ApplicationRunner"))
                .magenta("We've started our application with non option args %s", args.getNonOptionArgs())
                .magenta("We've started our application with option names %s", args.getOptionNames())
                .orange()
                .outList("This is one option name %s for value %s", ConsolerizerVars.createListFromObjects(collect))
                .magenta("We've started our application with an original source of %s", Arrays.stream(args.getSourceArgs()).collect(Collectors.toList()))
                .blue()
                .bgBlue()
                .unicorns(100)
                .bgOrange("And this is how start")
                .reset();
    }
}
