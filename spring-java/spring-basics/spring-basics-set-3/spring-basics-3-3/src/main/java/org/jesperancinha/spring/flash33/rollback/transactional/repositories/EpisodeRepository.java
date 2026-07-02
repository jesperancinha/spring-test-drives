package org.jesperancinha.spring.flash33.rollback.transactional.repositories;

import org.jesperancinha.spring.flash33.rollback.transactional.domain.Episode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EpisodeRepository extends JpaRepository<Episode, Long> {
}
