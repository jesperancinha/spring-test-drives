package org.jesperancinha.sftd.mastery2.portuguese.music.services

import org.assertj.core.api.Assertions
import org.jesperancinha.sftd.mastery2.portuguese.music.api.ArtistService
import org.jesperancinha.sftd.mastery2.portuguese.music.configuration.TestDatabaseConfiguration
import org.jesperancinha.sftd.mastery2.portuguese.music.repositories.ArtistRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.context.annotation.Import
import org.springframework.jdbc.datasource.init.DatabasePopulator
import org.springframework.test.context.ContextConfiguration
import org.springframework.transaction.annotation.Transactional
import java.sql.SQLException
import javax.sql.DataSource

@Transactional
@SpringBootTest(webEnvironment = RANDOM_PORT)
@ContextConfiguration
@Import(
    TestDatabaseConfiguration::class
)
internal class ArtistServiceImplDbPopulatorKotlinTest @Autowired constructor(
    private val artistService: ArtistService,
    private val databasePopulator: DatabasePopulator,
    private val dataSource: DataSource,
    private val artistRepository: ArtistRepository
) {
    @BeforeEach
    fun setUp(){
        artistRepository.deleteAll()
    }

    @Test
    @Throws(SQLException::class)
    fun testListArtistsWithSQLWhenListAllThenGetAList() {
        databasePopulator.populate(dataSource.connection)
        val artists = artistService.listArtists()
        Assertions.assertThat(artists).hasSize(4)
        val actual = artists[0]
        Assertions.assertThat(actual.name).isEqualTo("UHF")
        Assertions.assertThat(actual.nationality).isEqualTo("Portuguese")
        val actual2 = artists[1]
        Assertions.assertThat(actual2.name).isEqualTo("Radio Macau")
        Assertions.assertThat(actual2.nationality).isEqualTo("Portuguese")
        val actual3 = artists[2]
        Assertions.assertThat(actual3.name).isEqualTo("Humanos")
        Assertions.assertThat(actual3.nationality).isEqualTo("Portuguese")
        val actual4 = artists[3]
        Assertions.assertThat(actual4.name).isEqualTo("Mler Ife Dada")
        Assertions.assertThat(actual4.nationality).isEqualTo("Portuguese")
    }
}