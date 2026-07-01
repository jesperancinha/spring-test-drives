package org.jesperancinha.sftd.mastery1.french.music.dao;

import org.jesperancinha.sftd.mastery1.french.music.domain.Member;

import java.util.List;

public interface MemberDao {
    List<Member> loadAll();

    Long create(Member member);

    Member read(Long id);

    Long update(Member member);

    boolean delete(Long id);
}
