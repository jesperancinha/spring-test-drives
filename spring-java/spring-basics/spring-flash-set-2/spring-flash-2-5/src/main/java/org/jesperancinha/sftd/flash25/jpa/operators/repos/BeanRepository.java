package org.jesperancinha.sftd.flash25.jpa.operators.repos;

import org.jesperancinha.sftd.flash25.jpa.operators.domain.Bean;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BeanRepository extends JpaRepository<Bean, Long> {
    Bean findFirstByNameLike(final String name);

    Bean findDistinctTopByKilosOrderByNameAsc(final Long kilos);

    List<Bean> findByNameContains(final String word);

    List<Bean> findByNameLike(final String name);

    List<Bean> findByKilosLessThan(final Long kilos);

    List<Bean> findByKilosGreaterThan(final Long kilos);

    List<Bean> findByKilosBetween(final Long kilos1, final Long kilos2);

    Bean findByNameIs(final String name);

    List<Bean> findByKilosNull();

    Long countBeansByNameIgnoreCase(String name);

    List<Bean> findByNameNot(String name);

    void deleteById(Long id);
}
