package org.jesperancinha.sftd.controller

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer
import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer.title
import org.jesperancinha.sftd.old.webapp.model.Film
import org.jesperancinha.sftd.old.webapp.model.FilmGenre
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("movies")
class MovieLinesController {

    @GetMapping(path = ["thesoundofmusic"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getQuotes(): String {
        return "The hills are alive with the sound of music";
    }

    @GetMapping(path = ["list"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun allFilms(): List<Film> {
        ConsolerizerComposer.outSpace()
            .ln()
            .black()
            .bgYellow(title("Method list"))
            .reset()
        return arrayListOf(
            Film(
                title = "The sound of music",
                genre = FilmGenre.ROMANTIC,
                nationality = "USA"
            ),
            Film(
                title = "Os Olhos Azuis de Yonta",
                genre = FilmGenre.HISTORIC,
                nationality = "Portugal"
            )
        )
    }
}