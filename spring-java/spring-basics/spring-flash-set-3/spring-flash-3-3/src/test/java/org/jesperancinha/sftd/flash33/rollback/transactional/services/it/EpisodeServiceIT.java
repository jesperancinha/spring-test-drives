package org.jesperancinha.sftd.flash33.rollback.transactional.services.it;

import org.jesperancinha.sftd.flash33.rollback.transactional.domain.Episode;
import org.jesperancinha.sftd.flash33.rollback.transactional.dto.EpisodeDto;
import org.jesperancinha.sftd.flash33.rollback.transactional.exceptions.EpisodeException;
import org.jesperancinha.sftd.flash33.rollback.transactional.exceptions.VideoCountryException;
import org.jesperancinha.sftd.flash33.rollback.transactional.repositories.EpisodeRepository;
import org.jesperancinha.sftd.flash33.rollback.transactional.services.EpisodeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@SpringBootTest
class EpisodeServiceIT {

    @Autowired
    private EpisodeRepository episodeRepository;

    @Autowired
    private EpisodeService episodeService;

    @BeforeEach
    public void setUp() {
        episodeRepository.deleteAll();
    }

    @Test
    void testCreateEpisode_whenCreateOne_thenCallSave() {
        final var episodeDto = EpisodeDto.builder().id(1L).name("The eyes see more").build();

        assertThatExceptionOfType(EpisodeException.class)
                .isThrownBy(() -> episodeService.createEpisode(episodeDto));

        final var all = episodeRepository.findAll();

        assertThat(all).isNotNull();
        assertThat(all).hasSize(1);
        final var episode = all.get(0);
        assertThat(episode).isNotNull();
        assertThat(episode.getId()).isNotNull();
        assertThat(episode.getId()).isBetween(1L, 10L);
        assertThat(episode.getName()).isEqualTo(episodeDto.getName());
    }

    @Test
    void testCreateEpisodeExceptionRollback_whenCreateOne_thenCallSave() {
        final var episodeDto = EpisodeDto.builder().id(1L).name("The eyes see more").build();

        assertThatExceptionOfType(VideoCountryException.class)
                .isThrownBy(() -> episodeService.createEpisodeExceptionRollback(episodeDto));

        final var all = episodeRepository.findAll();

        assertThat(all).isNotNull();
        assertThat(all).hasSize(0);
    }

    @Test
    void testCreateEpisodeExceptionNoRollback_whenCreateOne_thenCallSave() {
        final var episodeDto = EpisodeDto.builder().id(1L).name("The eyes see more").build();

        assertThatExceptionOfType(EpisodeException.class)
                .isThrownBy(() -> episodeService.createEpisodeExceptionNoRollback(episodeDto));

        final var all = episodeRepository.findAll();

        assertThat(all).isNotNull();
        assertThat(all).hasSize(1);
        final var episode = all.get(0);
        assertThat(episode).isNotNull();
        assertThat(episode.getId()).isNotNull();
        assertThat(episode.getId()).isBetween(1L, 10L);
        assertThat(episode.getName()).isEqualTo(episodeDto.getName());
    }

    @Test
    void testCreateEpisodeMixRollback_whenCreateOne_thenCallSave() {
        final var episodeDto = EpisodeDto.builder().id(1L).name("The eyes see more").build();

        assertThatExceptionOfType(VideoCountryException.class)
                .isThrownBy(() -> episodeService.createEpisodeMixRollback(episodeDto));

        final var all = episodeRepository.findAll();

        assertThat(all).isNotNull();
        assertThat(all).hasSize(0);
    }

    @Test
    void testCreateEpisodeMixNoRollback_whenCreateOne_thenCallSave() {
        final var episodeDto = EpisodeDto.builder().id(1L).name("The eyes see more").build();

        assertThatExceptionOfType(EpisodeException.class)
                .isThrownBy(() -> episodeService.createEpisodeMixNoRollback(episodeDto));

        final var all = episodeRepository.findAll();

        assertThat(all).isNotNull();
        assertThat(all).hasSize(1);
        final var episode = all.get(0);
        assertThat(episode).isNotNull();
        assertThat(episode.getId()).isNotNull();
        assertThat(episode.getId()).isBetween(1L, 10L);
        assertThat(episode.getName()).isEqualTo(episodeDto.getName());
    }

    @Test
    void testGetEpisodeById_whenGetById1_thenGetEpisode1() {
        final var episode = Episode.builder().id(1L).name("The eyes see more").build();
        final var savedEpisode = episodeRepository.save(episode);

        final var resultEpisodeDto = episodeService.getEpisodeById(savedEpisode.getId());

        assertThat(resultEpisodeDto).isNotNull();
        assertThat(resultEpisodeDto.getId()).isEqualTo(savedEpisode.getId());
        assertThat(resultEpisodeDto.getName()).isEqualTo(savedEpisode.getName());
    }

    @Test
    void testGetAllEpisodes_whenGetAll_thenListAllEpisodes() {
        final var episode = Episode.builder().id(1L).name("The eyes see more").build();
        final var savedEpisode = episodeRepository.save(episode);

        final var resultEpisodeDtos = episodeService.getAllEpisodes();

        assertThat(resultEpisodeDtos).isNotNull();
        assertThat(resultEpisodeDtos).hasSize(1);
        final var episodeDto = resultEpisodeDtos.get(0);
        assertThat(episodeDto).isNotNull();
        assertThat(episodeDto.getId()).isEqualTo(savedEpisode.getId());
        assertThat(episodeDto.getName()).isEqualTo(savedEpisode.getName());
    }
}