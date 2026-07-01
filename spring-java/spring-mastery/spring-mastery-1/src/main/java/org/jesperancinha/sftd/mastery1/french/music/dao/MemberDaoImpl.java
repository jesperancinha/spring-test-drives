package org.jesperancinha.sftd.mastery1.french.music.dao;

import org.jesperancinha.sftd.mastery1.french.music.api.MemberService;
import org.jesperancinha.sftd.mastery1.french.music.domain.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.OptionalLong;

@Component
public class MemberDaoImpl implements MemberDao {

    private final JdbcTemplate jdbcTemplate;

    private final MemberService memberService;

    public MemberDaoImpl(JdbcTemplate jdbcTemplate, MemberService memberService) {
        this.jdbcTemplate = jdbcTemplate;
        this.memberService = memberService;
    }

    @Override
    public List<Member> loadAll() {
        return null;
    }

    @Override
    public Long create(Member member) {
        String sql = "insert into member (id, name, join_date) values (?, ?, ?)";
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection)
                    throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);
                ps.setLong(1, getMaxId());
                ps.setString(2, member.getName());
                ps.setObject(3, member.getJoinDate());
                return ps;
            }
        }, holder);
        Number key = holder.getKey();
        if (key != null) {
            return key.longValue();
        }
        throw new RuntimeException("No generated primary key returned.");
    }

    private Long getMaxId() {
        final List<Member> allMembers = memberService.getAllMembers();
        final OptionalLong max = allMembers.stream().map(Member::getId).mapToLong(x -> x).max();
        return max.orElse(0) + 1L;
    }

    @Override
    public Member read(Long id) {
        return null;
    }

    @Override
    public Long update(Member member) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
