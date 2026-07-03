package org.jesperancinha.spring.mastery1.french.music.repository;

import org.jesperancinha.spring.mastery1.french.music.domain.Label;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabelRepository extends JpaRepository<Label, Long> {
}
