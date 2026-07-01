package org.jesperancinha.sftd.mastery1.french.music.repository;

import org.jesperancinha.sftd.mastery1.french.music.domain.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface ArtistRepository extends JpaRepository<Artist, Long> {
    List<Artist> findArtistByNameLike(String param);
}
