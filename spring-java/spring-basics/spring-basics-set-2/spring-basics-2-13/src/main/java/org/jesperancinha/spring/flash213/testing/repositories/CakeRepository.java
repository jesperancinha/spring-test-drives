package org.jesperancinha.spring.flash213.testing.repositories;

import org.jesperancinha.spring.flash213.testing.model.Cake;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CakeRepository extends JpaRepository<Cake, Long> {
}
