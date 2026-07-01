package org.jesperancinha.sftd.mastery2.portuguese.music.repositories;

import org.jesperancinha.sftd.mastery2.portuguese.music.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long> {

    Artist findArtistByNameLike(final String searchItem);
}
