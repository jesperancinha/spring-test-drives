package org.jesperancinha.sftd.flash19.transactional.controllers;

import jakarta.servlet.ServletException;
import org.jesperancinha.sftd.flash19.transactional.containers.AbstractTestContainersIT;
import org.jesperancinha.sftd.flash19.transactional.domain.Album;
import org.jesperancinha.sftd.flash19.transactional.dto.AlbumDto;
import org.jesperancinha.sftd.flash19.transactional.repos.AlbumRepository;
import org.jesperancinha.sftd.flash19.transactional.services.AlbumService;
import org.jesperancinha.sftd.flash19.transactional.services.AlbumServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(AlbumController.class)
@EnableTransactionManagement
@ContextConfiguration(classes = {AlbumServiceImpl.class, AlbumController.class}, initializers = AbstractTestContainersIT.DockerPostgresDataInitializer.class)
class AlbumControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AlbumRepository albumRepository;

    @Autowired
    private AlbumService albumService;

    @Test
    void getAllAlbums() {
    }

    @Test
    void createAlbum() {
    }

    @Test
    void testCreateAlbumRollBackWhenCallCreateAlbumThenCreateAndRollback() {
        final var inputAlbum = new AlbumDto();
        inputAlbum.setName("The Abbey Road Sessions");
        inputAlbum.setArtist("Kylie Minogue");
        inputAlbum.setPublisher("EMI");
        inputAlbum.setYear(2012L);

        when(albumRepository.save(any(Album.class))).thenAnswer(invocationOnMock -> invocationOnMock.getArguments()[0]);

        assertThrows(ServletException.class,
                () -> mockMvc.perform(post("/create/albumRollback")
                        .header("name", "The Abbey Road Sessions")
                        .header("artist", "Kylie Minogue")
                        .header("publisher", "EMI")
                        .header("year", 2012)));

        assertThat(albumService.getLastTry()).isNull();
    }

    @Test
    void updateAlbum() {
    }

    @Test
    void testCreateAlbum() {
    }
}