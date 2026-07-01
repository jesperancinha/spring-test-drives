package org.jesperancinha.sftd.mastery2.portuguese.music.services;

import org.jesperancinha.sftd.mastery2.portuguese.music.api.ArtistService;
import org.jesperancinha.sftd.mastery2.portuguese.music.model.Artist;
import org.jesperancinha.sftd.mastery2.portuguese.music.repositories.ArtistRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepository artistRepository;

    public ArtistServiceImpl(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public List<Artist> listArtists() {
        return artistRepository.findAll();
    }

    @Override
    @PreAuthorize("authenticated and hasRole('ROLE_ADMIN')")
    public Artist getArtistByName(final String name) {
        return artistRepository.findArtistByNameLike("%".concat(name).concat("%"));
    }
}
