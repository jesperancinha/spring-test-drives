package org.jesperancinha.spring.tutorial.persistence.rest.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PotatoRepository extends JpaRepository<Potato, Long> {
}
