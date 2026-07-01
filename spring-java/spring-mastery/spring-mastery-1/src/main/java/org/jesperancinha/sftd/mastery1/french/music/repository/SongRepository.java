package org.jesperancinha.sftd.mastery1.french.music.repository;

import org.jesperancinha.sftd.mastery1.french.music.domain.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, Long> {
}
