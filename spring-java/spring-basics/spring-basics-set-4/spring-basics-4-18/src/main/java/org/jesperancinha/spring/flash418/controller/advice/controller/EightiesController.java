package org.jesperancinha.spring.flash418.controller.advice.controller;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.jesperancinha.spring.flash418.controller.advice.NotEightiesMusicException;
import org.jesperancinha.spring.flash418.controller.advice.Song;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class EightiesController {

    @PutMapping(path = "/",
            consumes = APPLICATION_JSON_VALUE)
    public boolean checkEighties(
            @RequestBody
            final Song song) {
        if (song.hitDate().isAfter(LocalDate.of(1980, 1, 1))
                && song.hitDate().isBefore(LocalDate.of(1990, 1, 1))) {
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
