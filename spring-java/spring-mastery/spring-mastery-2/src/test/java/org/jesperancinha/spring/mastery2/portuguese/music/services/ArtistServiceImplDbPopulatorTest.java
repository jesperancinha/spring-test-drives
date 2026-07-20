package org.jesperancinha.spring.mastery2.portuguese.music.services;

import org.jesperancinha.spring.mastery2.portuguese.music.api.ArtistService;
import org.jesperancinha.spring.mastery2.portuguese.music.configuration.TestDatabaseConfiguration;
import org.jesperancinha.spring.mastery2.portuguese.music.repositories.ArtistRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@Transactional
@SpringBootTest(webEnvironment = RANDOM_PORT)
@ContextConfiguration
@Import(TestDatabaseConfiguration.class)
@ExtendWith(SpringExtension.class)
class ArtistServiceImplDbPopulatorTest {

    @Autowired
    private ArtistService artistService;

    @Autowired
    private DatabasePopulator databasePopulator;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private ArtistRepository artistRepository;

    @BeforeEach
    void setUp(){
        artistRepository.deleteAll();
    }

    @Test
    void testListArtistsWithSQLWhenListAllThenGetAList() throws SQLException {
        databasePopulator.populate(dataSource.getConnection());
        final var artists = artistService.listArtists();

        assertThat(artists).hasSizeGreaterThanOrEqualTo(4);
        final var actual = artists.stream().filter(artist -> artist.getName().equals("UHF"))
                .findFirst().orElseThrow();
        assertThat(actual.getName()).isEqualTo("UHF");
        assertThat(actual.getNationality()).isEqualTo("Portuguese");
        final var actual2 = artists.stream().filter(artist -> artist.getName().equals("Radio Macau"))
                .findFirst().orElseThrow();
        assertThat(actual2.getName()).isEqualTo("Radio Macau");
        assertThat(actual2.getNationality()).isEqualTo("Portuguese");
        final var actual3 = artists.stream().filter(artist -> artist.getName().equals("Humanos"))
                .findFirst().orElseThrow();
        assertThat(actual3.getName()).isEqualTo("Humanos");
        assertThat(actual3.getNationality()).isEqualTo("Portuguese");
        final var actual4 = artists.stream().filter(artist -> artist.getName().equals("Mler Ife Dada"))
                .findFirst().orElseThrow();
        assertThat(actual4.getName()).isEqualTo("Mler Ife Dada");
        assertThat(actual4.getNationality()).isEqualTo("Portuguese");

    }
}