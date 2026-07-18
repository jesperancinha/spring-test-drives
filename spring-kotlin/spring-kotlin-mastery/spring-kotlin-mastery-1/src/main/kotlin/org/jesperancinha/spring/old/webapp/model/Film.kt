package org.jesperancinha.spring.old.webapp.model

data class Film(
    val id: String = "",
    val title: String,
    val genre: FilmGenre,
    val nationality: String
)

