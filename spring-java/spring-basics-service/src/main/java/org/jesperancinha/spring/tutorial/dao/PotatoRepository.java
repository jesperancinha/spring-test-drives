package org.jesperancinha.spring.tutorial.dao;

import org.jesperancinha.spring.tutorial.domain.Potato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PotatoRepository extends JpaRepository<Potato, Long> {
}
