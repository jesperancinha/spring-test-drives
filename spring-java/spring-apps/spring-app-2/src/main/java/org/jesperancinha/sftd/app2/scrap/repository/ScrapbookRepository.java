package org.jesperancinha.sftd.app2.scrap.repository;

import org.jesperancinha.sftd.app2.scrap.model.Scrapbook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScrapbookRepository extends JpaRepository<Scrapbook, Long> {
    @Query(nativeQuery = true,
            value = "SELECT * FROM Scrapbook WHERE name regexp ?1")
    List<Scrapbook> findScrapbookByNameRegex(String regex);
}
