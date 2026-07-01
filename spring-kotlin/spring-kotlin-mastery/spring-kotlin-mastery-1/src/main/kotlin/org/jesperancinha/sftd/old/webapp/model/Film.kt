package org.jesperancinha.sftd.old.webapp.model

data class Film(
    var id: String = "",
    var title: String,
    var genre: FilmGenre,
    var nationality: String
)

