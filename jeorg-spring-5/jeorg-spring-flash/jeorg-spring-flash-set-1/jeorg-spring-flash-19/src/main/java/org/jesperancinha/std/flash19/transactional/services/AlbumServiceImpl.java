package org.jesperancinha.std.flash19.transactional.services;

import org.jesperancinha.console.consolerizer.common.ConsolerizerColor;
import org.jesperancinha.std.flash19.transactional.domain.Album;
import org.jesperancinha.std.flash19.transactional.domain.AlbumRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {

    private AlbumRepository albumRepository;

    public AlbumServiceImpl(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    private Album createAlbum(Album album) {
        return albumRepository.save(album);
    }

    @Override
    public boolean deleteAlbumByIdI(Long id) {
        albumRepository.deleteById(id);
        return true;
    }

    @Override
    public Album updateAlbum(Album album) {
        return albumRepository.save(album);
    }

    @Override
    public Album createAlbum(String name, String artist, String publisher, Long year) {
        final var album = new Album();
        album.setName(name);
        album.setArtist(artist);
        album.setPublisher(publisher);
        album.setYear(year);
        return createAlbum(album);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW,
            rollbackFor = RuntimeException.class)
    public Album createAlbumRollBack(String name, String artist, String publisher, Long year) {
        final var album = new Album();
        album.setName(name);
        album.setArtist(artist);
        album.setPublisher(publisher);
        album.setYear(year);
        createAlbum(album);
        throw new RuntimeException(ConsolerizerColor.RED.getPBText("Your album %s was not saved!", album));
    }

    @Override
    public List<Album> getAllAlbums() {
        return this.albumRepository.findAll();
    }
}
