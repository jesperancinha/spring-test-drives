package org.jesperancinha.sftd.mastery1.french.music.repository;

import org.jesperancinha.sftd.mastery1.french.music.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findAllByNameLike(String param);

    @Query("select m from Member m where m.joinDate is not null")
    List<Member> getAllDateNonNullMembers();
}
