package org.jesperancinha.spring.mastery1.french.music.repository;

import org.jesperancinha.spring.mastery1.french.music.domain.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, Long> {
}
