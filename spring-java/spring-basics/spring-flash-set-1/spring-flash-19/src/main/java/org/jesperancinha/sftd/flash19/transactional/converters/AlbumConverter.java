package org.jesperancinha.sftd.flash19.transactional.converters;

import org.jesperancinha.sftd.flash19.transactional.domain.Album;
import org.jesperancinha.sftd.flash19.transactional.dto.AlbumDto;

public class AlbumConverter {
    public static AlbumDto toDto(Album album) {
        final var albumDto = new AlbumDto();
        albumDto.setArtist(album.getArtist());
        albumDto.setName(album.getName());
        albumDto.setYear(album.getYear());
        albumDto.setPublisher(album.getPublisher());
        return albumDto;
    }

    public static Album toData(AlbumDto albumDto) {
        final var album = new Album();
        album.setArtist(albumDto.getArtist());
        album.setName(albumDto.getName());
        album.setYear(albumDto.getYear());
        album.setPublisher(albumDto.getPublisher());
        return album;
    }
}
