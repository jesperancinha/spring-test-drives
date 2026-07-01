package org.jesperancinha.sftd.flash19.transactional.controllers;

import org.jesperancinha.sftd.flash19.transactional.domain.Album;
import org.jesperancinha.sftd.flash19.transactional.dto.AlbumDto;
import org.jesperancinha.sftd.flash19.transactional.repos.AlbumRepository;
import org.jesperancinha.sftd.flash19.transactional.services.AlbumServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AlbumControllerUnitTest {

    @Mock
    private AlbumRepository albumRepository;

    @InjectMocks
    private AlbumServiceImpl albumService;

    @Test
    void testGetAllAlbumsWhenCallThenGetAllAlbums(
            @Mock
            final List<Album> albums) {
        when(albumRepository.findAll()).thenReturn(albums);

        final List<AlbumDto> allAlbums = albumService.getAllAlbums();

        assertThat(allAlbums).hasSize(albums.size());
    }

    @Test
    void createAlbumRollBack() {
    }

    @Test
    void updateAlbum() {
    }

    @Test
    void testCreateAlbum() {
    }
}