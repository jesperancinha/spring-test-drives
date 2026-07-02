package org.jesperancinha.sftd.flash19.transactional.services;

import org.jesperancinha.sftd.flash19.transactional.converters.AlbumConverter;
import org.jesperancinha.sftd.flash19.transactional.domain.Album;
import org.jesperancinha.sftd.flash19.transactional.dto.AlbumDto;
import org.jesperancinha.sftd.flash19.transactional.repos.AlbumRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.RED;
import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

@Service
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;

    private Album lastTry;

    public AlbumServiceImpl(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    /**
     * Creates the album
     *
     * @param album {@link Album}
     * @return The created album {@link Album}
     */
    private Album createAlbum(Album album) {
        return albumRepository.save(album);
    }

    /**
     * Removes an album by its Id
     *
     * @param id {@link Long}
     * @return True if album has been deleted
     */
    @Override
    public boolean deleteAlbumByIdI(Long id) {
        albumRepository.deleteById(id);
        return true;
    }


    /**
     * Updates the album that has already been created
     *
     * @param album {@link Album}
     * @return Updated album {@link Album}
     */
    @Override
    public AlbumDto updateAlbum(AlbumDto album) {
        return AlbumConverter.toDto(albumRepository.save(AlbumConverter.toData(album)));
    }

    @Override
    public AlbumDto createAlbum(String name, String artist, String publisher, Long year) {
        final var album = new Album();
        album.setName(name);
        album.setArtist(artist);
        album.setPublisher(publisher);
        album.setYear(year);
        return AlbumConverter.toDto(createAlbum(album));
    }

    @Override
    @Transactional(propagation = REQUIRES_NEW,
            rollbackFor = RuntimeException.class)
    public AlbumDto createAlbumRollBack(String name, String artist, String publisher, Long year) {
        final var album = new Album();
        album.setName(name);
        album.setArtist(artist);
        album.setPublisher(publisher);
        album.setYear(year);
        createAlbum(album);
        this.lastTry = album;
        throw new RuntimeException(RED.getPBText("Your album %s was not saved!", album));
    }

    @Override
    public List<AlbumDto> getAllAlbums() {
        return this.albumRepository.findAll().stream().map(AlbumConverter::toDto).collect(Collectors.toList());
    }

    @Override
    public Album getLastTry() {
        return lastTry;
    }
}
