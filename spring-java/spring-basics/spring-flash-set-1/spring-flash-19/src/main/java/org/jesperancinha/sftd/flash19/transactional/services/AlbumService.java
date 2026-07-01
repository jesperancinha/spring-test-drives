package org.jesperancinha.sftd.flash19.transactional.services;

import org.jesperancinha.sftd.flash19.transactional.domain.Album;
import org.jesperancinha.sftd.flash19.transactional.dto.AlbumDto;

import java.util.List;

public interface AlbumService {
    boolean deleteAlbumByIdI(Long id);

    AlbumDto updateAlbum(AlbumDto album);

    AlbumDto createAlbum(String name, String artist, String publisher, Long year);

    AlbumDto createAlbumRollBack(String name, String artist, String publisher, Long year);

    List<AlbumDto> getAllAlbums();

    Album getLastTry();
}
