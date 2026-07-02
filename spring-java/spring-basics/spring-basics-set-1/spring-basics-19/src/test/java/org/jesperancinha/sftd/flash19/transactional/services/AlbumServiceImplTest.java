package org.jesperancinha.sftd.flash19.transactional.services;

import org.jesperancinha.sftd.flash19.transactional.domain.Album;
import org.jesperancinha.sftd.flash19.transactional.dto.AlbumDto;
import org.jesperancinha.sftd.flash19.transactional.repos.AlbumRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AlbumServiceImpl.class)
class AlbumServiceImplTest {

    @MockitoBean
    private AlbumRepository albumRepository;

    @Autowired
    private AlbumServiceImpl albumService;

    @Captor
    private ArgumentCaptor<Album> albumArgumentCaptor;

    @Captor
    private ArgumentCaptor<Long> longArgumentCaptor;

    @Test
    void testDeleteAlbumByIdIWhenRemovingPerIdThenReturnTrueOnDelete() {
        final boolean deleted = albumService.deleteAlbumByIdI(1L);
        assertThat(deleted).isTrue();

        verify(albumRepository, only()).deleteById(longArgumentCaptor.capture());
        assertThat(longArgumentCaptor.getValue()).isEqualTo(1L);
    }

    @Test
    void testUpdateAlbumWhenUpdatingAlgumThenReturnUpdatedAlbum() {
        when(albumRepository.save(any(Album.class))).thenAnswer(invocationOnMock -> invocationOnMock.getArguments()[0]);
        final var inputAlbum = new AlbumDto();
        inputAlbum.setName("The Abbey Road Sessions");
        inputAlbum.setArtist("Kylie Minogue");
        inputAlbum.setPublisher("EMI");
        inputAlbum.setYear(2012L);

        final var album = albumService.updateAlbum(inputAlbum);

        verify(albumRepository, times(1)).save(albumArgumentCaptor.capture());
        final var albumCapture = albumArgumentCaptor.getValue();
        assertThat(albumCapture.getName()).isEqualTo("The Abbey Road Sessions");
        assertThat(albumCapture.getArtist()).isEqualTo("Kylie Minogue");
        assertThat(albumCapture.getPublisher()).isEqualTo("EMI");
        assertThat(albumCapture.getYear()).isEqualTo(2012L);
        assertThat(album.getName()).isEqualTo("The Abbey Road Sessions");
        assertThat(album.getArtist()).isEqualTo("Kylie Minogue");
        assertThat(album.getPublisher()).isEqualTo("EMI");
        assertThat(album.getYear()).isEqualTo(2012L);
    }

    @Test
    void testCreateAlbumWhenCreatingAlbumThenReturnCreatedAlbum() {
        when(albumRepository.save(any(Album.class))).thenAnswer(invocationOnMock -> invocationOnMock.getArguments()[0]);

        final AlbumDto album = albumService.createAlbum(
                "A state of Trance 2006", "Armin van Buuren", "Armada", 2006L);

        verify(albumRepository, times(1)).save(albumArgumentCaptor.capture());
        final var albumCapture = albumArgumentCaptor.getValue();
        assertThat(albumCapture.getName()).isEqualTo("A state of Trance 2006");
        assertThat(albumCapture.getArtist()).isEqualTo("Armin van Buuren");
        assertThat(albumCapture.getPublisher()).isEqualTo("Armada");
        assertThat(albumCapture.getYear()).isEqualTo(2006L);
        assertThat(album.getName()).isEqualTo("A state of Trance 2006");
        assertThat(album.getArtist()).isEqualTo("Armin van Buuren");
        assertThat(album.getPublisher()).isEqualTo("Armada");
        assertThat(album.getYear()).isEqualTo(2006L);
    }

    @Test
    void testCreateAlbumRollBackWhenCallThenSimulatePerformARollback() {
        assertThrows(RuntimeException.class, () ->
                albumService.createAlbumRollBack(
                        "The Downward Spiral", "Nine Inch Nails", "Nothing Records", 1995L));

        verify(albumRepository, times(1)).save(albumArgumentCaptor.capture());
        final Album value = albumArgumentCaptor.getValue();
        assertThat(value.getId()).isNull();
        assertThat(value.getName()).isEqualTo("The Downward Spiral");
        assertThat(value.getArtist()).isEqualTo("Nine Inch Nails");
        assertThat(value.getPublisher()).isEqualTo("Nothing Records");
        assertThat(value.getYear()).isEqualTo(1995L);
    }

    @Test
    void testGetAllAlbumsWhenCallThenGetAllAlbums() {
        final List<Album> albums = new ArrayList<>();
        when(albumRepository.findAll()).thenReturn(albums);

        final List<AlbumDto> allAlbums = albumService.getAllAlbums();

        assertThat(allAlbums).hasSize(albums.size());
    }
}