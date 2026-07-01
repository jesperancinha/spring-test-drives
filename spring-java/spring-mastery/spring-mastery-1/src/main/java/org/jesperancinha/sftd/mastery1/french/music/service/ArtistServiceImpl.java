package org.jesperancinha.sftd.mastery1.french.music.service;

import org.jesperancinha.sftd.mastery1.french.music.api.ArtistService;
import org.jesperancinha.sftd.mastery1.french.music.domain.Artist;
import org.jesperancinha.sftd.mastery1.french.music.repository.ArtistRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepository artistRepository;

    public ArtistServiceImpl(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public List<Artist> getAllArtists() {
        return artistRepository.findAll();
    }

    @Override
    public List<Artist> getArtistsLike(String param) {
        return artistRepository.findArtistByNameLike(param);
    }

    @Override
    @Transactional(
            propagation = Propagation.REQUIRES_NEW,
            isolation = Isolation.SERIALIZABLE,
            rollbackFor = RuntimeException.class,
            rollbackForClassName = "RuntimeException",
            noRollbackForClassName = "Error",
            noRollbackFor = Error.class)
    public void deleteArtistById(Long id) {
        artistRepository.deleteById(id);
    }
}
