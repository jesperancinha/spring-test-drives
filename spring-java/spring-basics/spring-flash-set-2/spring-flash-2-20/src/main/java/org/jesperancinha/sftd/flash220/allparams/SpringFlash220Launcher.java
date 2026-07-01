package org.jesperancinha.sftd.flash220.allparams;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.BRIGHT_RED;
import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.MAGENTA;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@SpringBootApplication
@RestController
public class SpringFlash220Launcher {

    private final List<String> SEVEN_SINS = Arrays.asList("Gluttony", "Lust", "Greed", "Wrath", "Sloth", "Envy", "Pride");

    public static void main(String[] args) {
        SpringApplication.run(SpringFlash220Launcher.class, args);
    }

    @PostMapping(path = "/matrix/{test}",
            produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> postAllSinsMatrix(
            @PathVariable
            final String test,
            @MatrixVariable(name = "sin1",
                    pathVar = "test")
            final String sin1,
            @MatrixVariable
            final String sin2,
            @MatrixVariable
            final String sin3,
            @MatrixVariable
            final String sin4,
            @MatrixVariable
            final String sin5,
            @MatrixVariable
            final String sin6,
            @MatrixVariable
            final String sin7) {

        final var answers = Arrays.asList(sin1, sin2, sin3, sin4, sin5, sin6, sin7);
        Collections.sort(answers);
        Collections.sort(SEVEN_SINS);
        if (!answers.equals(SEVEN_SINS)) {
            return ResponseEntity.ok("You are not wise! Try again and be wise 😊");
        }

        BRIGHT_RED.printGenericLn("Showing all on path %s", test);
        MAGENTA.printGenericLn("7:");
        MAGENTA.printGenericLn(sin1);
        MAGENTA.printGenericLn(sin2);
        MAGENTA.printGenericLn(sin3);
        MAGENTA.printGenericLn(sin4);
        MAGENTA.printGenericLn(sin5);
        MAGENTA.printGenericLn(sin6);
        MAGENTA.printGenericLn(sin7);

        return ResponseEntity.ok("You are wise!");
    }

    @PostMapping(path = "/request/{sin1}/{sin2}",
            produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> postAllSinsRequest(
            @PathVariable
            final String sin1,
            @PathVariable
            final String sin2,
            @RequestParam
            final String sin3,
            @RequestParam
            final String sin4,
            @RequestHeader
            final String sin5,
            @RequestHeader
            final String sin6,
            @RequestHeader
            final String sin7) {

        final var answers = Arrays.asList(sin1, sin2, sin3, sin4, sin5, sin6, sin7);
        Collections.sort(answers);
        Collections.sort(SEVEN_SINS);
        if (!answers.equals(SEVEN_SINS)) {
            return ResponseEntity.ok("You are not wise! Try again and be wise 😊");
        }
        BRIGHT_RED.printGenericLn("Showing all on path wow");
        MAGENTA.printGenericLn("7:");
        MAGENTA.printGenericLn(sin1);
        MAGENTA.printGenericLn(sin2);
        MAGENTA.printGenericLn(sin3);
        MAGENTA.printGenericLn(sin4);
        MAGENTA.printGenericLn(sin5);
        MAGENTA.printGenericLn(sin6);
        MAGENTA.printGenericLn(sin7);
        return ResponseEntity.ok("You are wise!");

    }
}
