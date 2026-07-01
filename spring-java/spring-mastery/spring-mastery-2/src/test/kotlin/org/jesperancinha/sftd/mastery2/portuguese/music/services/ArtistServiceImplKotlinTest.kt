package org.jesperancinha.sftd.mastery2.portuguese.music.services

import org.assertj.core.api.Assertions
import org.jesperancinha.sftd.mastery2.portuguese.music.api.ArtistService
import org.jesperancinha.sftd.mastery2.portuguese.music.model.Artist
import org.jesperancinha.sftd.mastery2.portuguese.music.repositories.ArtistRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.jdbc.Sql.ExecutionPhase
import org.springframework.test.context.jdbc.SqlConfig
import org.springframework.test.context.jdbc.SqlGroup
import org.springframework.transaction.annotation.Transactional

@Transactional
@SpringBootTest(webEnvironment = RANDOM_PORT)
@ContextConfiguration
internal class ArtistServiceImplKotlinTest @Autowired constructor(
    private val artistService: ArtistService,
    private val artistRepository: ArtistRepository,
) {
    @BeforeEach
    fun setUp() {
        val artist = Artist()
        artist.name = "António Variações"
        artist.nationality = "Portuguese"
        artistRepository.save(artist)
    }

    @Test
    fun testListArtistsWhenListAllThenGetAList() {
        val artists = artistService.listArtists()
        Assertions.assertThat(artists).hasSize(1)
        val actual = artists[0]
        Assertions.assertThat(actual.name).isEqualTo("António Variações")
        Assertions.assertThat(actual.nationality).isEqualTo("Portuguese")
    }

    @Test
    @SqlGroup(
        Sql(scripts = ["classpath:artists.sql"]),
        Sql(
            scripts = ["classpath:cleanup.sql"],
            executionPhase = ExecutionPhase.AFTER_TEST_METHOD,
            config = SqlConfig(transactionMode = SqlConfig.TransactionMode.ISOLATED)
        )
    )
    fun testListArtistsWithSQLWhenListAllThenGetAList() {
        val artists = artistService.listArtists()
        Assertions.assertThat(artists).hasSize(2)
        val actual = artists[0]
        Assertions.assertThat(actual.name).isEqualTo("António Variações")
        Assertions.assertThat(actual.nationality).isEqualTo("Portuguese")
        val actual2 = artists[1]
        Assertions.assertThat(actual2.name).isEqualTo("Radio Macau")
        Assertions.assertThat(actual2.nationality).isEqualTo("Portuguese")
    }

    @Test
    fun testGetArtistByNameUnauthenticatedWhenGetArtistThenFail() {
        org.junit.jupiter.api.Assertions.assertThrows(
            AuthenticationCredentialsNotFoundException::class.java
        ) { artistService.getArtistByName("António") }
    }

    @Test
    @WithMockUser
    fun testGetArtistByNameUnauthorizerWhenGetArtistThenGetArtist() {
        org.junit.jupiter.api.Assertions.assertThrows(
            AccessDeniedException::class.java
        ) { artistService.getArtistByName("António") }
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = ["ADMIN"], value = "admin")
    fun testGetArtistByNameWhenGetArtistThenGetArtist() {
        val actual = artistService.getArtistByName("António")
        Assertions.assertThat(actual.name).isEqualTo("António Variações")
        Assertions.assertThat(actual.nationality).isEqualTo("Portuguese")
    }
}