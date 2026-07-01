package org.jesperancinha.sftd.flash418.controller.advice;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@SpringBootApplication
public class SpringFlash418Launcher {
    public static void main(String[] args) {
        SpringApplication.run(SpringFlash418Launcher.class, args);
    }

    @PutMapping(path = "/",
            consumes = APPLICATION_JSON_VALUE)
    public boolean checkEighties(
            @RequestBody
            final Song song) {
        if (song.getHitDate().isAfter(LocalDate.of(1980, 1, 1))
                && song.getHitDate().isBefore(LocalDate.of(1990, 1, 1))) {
            ConsolerizerComposer
                    .out(" ")
                    .blue("This song is an eighties music:")
                    .green(song)
                    .toConsoleLn();
            return true;
        }
        ConsolerizerComposer
                .out(" ")
                .blue("This song is not an eighties music:")
                .green(song)
                .toConsoleLn();
        throw new NotEightiesMusicException(song);
    }
}