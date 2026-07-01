package org.jesperancinha.sftd.mastery1.french.music.oauth.repository;

import org.jesperancinha.sftd.mastery1.french.music.oauth.domain.Mastery1User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Mastery1UserRepository extends JpaRepository<Mastery1User, String> {

    Mastery1User findUserByName(String name);
}