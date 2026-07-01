package org.jesperancinha.sftd.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.jesperancinha.sftd.old.webapp.model.Film
import org.jesperancinha.sftd.old.webapp.model.FilmGenre
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content

@WebMvcTest
internal class MovieLinesControllerTest(
    @Autowired
    val mockMvc: MockMvc
) {
    private val objectMapper: ObjectMapper = ObjectMapper()

    @Test
    fun testGetQuotesWhenCallingThenGetQuotes() {
        mockMvc.perform(get("/movies/thesoundofmusic"))
            .andExpect(content().string("The hills are alive with the sound of music"))
    }

    @Test
    fun testAllFilmsWhenCallingThenGetAllFilms() {
        mockMvc.perform(get("/movies/list"))
            .andExpect(
                content().json(
                    objectMapper.writeValueAsString(
                        listOf(
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
                    ),
                    false
                ),
            )
    }
}