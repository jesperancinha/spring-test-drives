package org.jesperancinha.spring.tutorial.persistence.rest.dao;

import org.jesperancinha.spring.tutorial.persistence.rest.domain.Potato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PotatoRepository extends JpaRepository<Potato, Long> {
}
