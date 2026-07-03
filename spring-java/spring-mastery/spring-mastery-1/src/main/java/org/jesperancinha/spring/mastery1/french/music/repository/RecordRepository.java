package org.jesperancinha.spring.mastery1.french.music.repository;


import org.jesperancinha.spring.mastery1.french.music.domain.Record;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<Record, Long> {
}
