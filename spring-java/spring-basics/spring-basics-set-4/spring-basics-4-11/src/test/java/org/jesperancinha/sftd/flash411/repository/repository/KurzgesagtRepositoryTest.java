package org.jesperancinha.sftd.flash411.repository.repository;

import org.jesperancinha.sftd.flash411.repository.domain.Kurzgesagt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class KurzgesagtRepositoryTest {

    @Autowired
    private KurzgesagtRepository kurzgesagtRepository;


    @BeforeEach
    public void setUp() {
        kurzgesagtRepository.save(Kurzgesagt
                .builder()
                .episode("The Ultimate Guide to Black Holes")
                .youtubeCode("QqsLTNkzvaY")
                .youTubePublication(LocalDate.of(2021, 4, 27))
                .build());
    }

    @Test
    @Transactional
    void testSaveWhenSavingThenSave() {
        final Kurzgesagt qqsLTNkzvaY = kurzgesagtRepository.save(Kurzgesagt
                .builder()
                .episode("The Ultimate Guide to Black Holes")
                .youtubeCode("QqsLTNkzvaY")
                .youTubePublication(LocalDate.of(2021, 4, 27))
                .build());

        assertThat(qqsLTNkzvaY).isNotNull();
        assertThat(qqsLTNkzvaY.getId()).isNotNull();
        assertThat(qqsLTNkzvaY.getEpisode()).isEqualTo("The Ultimate Guide to Black Holes");
        assertThat(qqsLTNkzvaY.getYoutubeCode()).isEqualTo("QqsLTNkzvaY");
        assertThat(qqsLTNkzvaY.getYouTubePublication()).isEqualTo(LocalDate.of(2021, 4, 27));
    }


    @Test
    @Transactional
    void testFindAllWhenFindingAllThenListAll() {
        final List<Kurzgesagt> all = kurzgesagtRepository.findAll();

        assertThat(all).isNotNull();
        assertThat(all).hasSize(1);
        final Kurzgesagt actual = all.get(0);
        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isNotNull();
        assertThat(actual.getEpisode()).isEqualTo("The Ultimate Guide to Black Holes");
        assertThat(actual.getYoutubeCode()).isEqualTo("QqsLTNkzvaY");
        assertThat(actual.getYouTubePublication()).isEqualTo(LocalDate.of(2021, 4, 27));
    }
}