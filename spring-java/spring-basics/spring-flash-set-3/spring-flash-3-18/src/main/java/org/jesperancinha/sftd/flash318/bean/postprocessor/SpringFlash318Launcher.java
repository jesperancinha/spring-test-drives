package org.jesperancinha.sftd.flash318.bean.postprocessor;

import org.jesperancinha.sftd.flash318.bean.postprocessor.domain.Recording;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.BRIGHT_GREEN;

@SpringBootApplication
public class SpringFlash318Launcher implements CommandLineRunner {

    private final Recording recording;

    public SpringFlash318Launcher(Recording recording) {
        this.recording = recording;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringFlash318Launcher.class, args);
    }

    @Override
    public void run(String... args) {
        BRIGHT_GREEN.printGenericLn(Arrays.asList(args));
        BRIGHT_GREEN.printGenericLn(recording);
    }
}