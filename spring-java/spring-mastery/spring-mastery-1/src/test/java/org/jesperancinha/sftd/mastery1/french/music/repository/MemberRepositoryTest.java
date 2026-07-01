package org.jesperancinha.sftd.mastery1.french.music.repository;

import org.jesperancinha.sftd.mastery1.french.music.configuration.Mastery1Configuration;
import org.jesperancinha.sftd.mastery1.french.music.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class MemberRepositoryTest {

    @MockitoBean
    private Mastery1Configuration mastery1Configuration;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @BeforeEach
    public void setUp() {
        artistRepository.deleteAll();
        memberRepository.deleteAll();
        final Member member = new Member();
        member.setName("Celine Dion");
        member.setJoinDate(LocalDate.ofYearDay(1981, 1));
        memberRepository.save(member);
    }

    @Test
    void testGetOneWhenFindOneThenMatch() {
        final Member member = memberRepository.getOne(1L);
        assertThat(member).isNotNull();
        assertThat(member.getName()).isEqualTo("Celine Dion");
        assertThat(member.getId()).isGreaterThanOrEqualTo(1L);
    }

    @Test
    void testFindAllByNameLikeWhenFindAllThenGetAll() {
        final List<Member> members = memberRepository.findAllByNameLike("%Dion%");
        assertThat(members).hasSize(1);
        final Member member = members.get(0);
        assertThat(member).isNotNull();
        assertThat(member.getName()).isEqualTo("Celine Dion");
        final LocalDate joinDate = member.getJoinDate();
        assertThat(joinDate).isNotNull();
        assertThat(joinDate.getYear()).isEqualTo(1981);
        assertThat(member.getId()).isGreaterThanOrEqualTo(1L);
    }

    @AfterEach
    public void tearDown() {
    }

}