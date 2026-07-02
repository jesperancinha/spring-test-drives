package org.jesperancinha.sftd.flash33.rollback.transactional.services;

import org.jesperancinha.sftd.flash33.rollback.transactional.domain.Episode;
import org.jesperancinha.sftd.flash33.rollback.transactional.dto.EpisodeDto;
import org.jesperancinha.sftd.flash33.rollback.transactional.exceptions.EpisodeException;
import org.jesperancinha.sftd.flash33.rollback.transactional.exceptions.VideoCountryException;
import org.jesperancinha.sftd.flash33.rollback.transactional.repositories.EpisodeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = EpisodeService.class)
class EpisodeServiceTest {

    @MockitoBean
    private EpisodeRepository episodeRepository;

    @Autowired
    private EpisodeService episodeService;

    @Test
    void testCreateEpisodeWhenCreateOneThenCallSave() {
        final var episodeDto = EpisodeDto.builder().id(1L).name("The eyes see more").build();

        assertThatExceptionOfType(EpisodeException.class)
                .isThrownBy(() -> episodeService.createEpisode(episodeDto));

        verify(episodeRepository, times(1))
                .save(Episode.builder().id(1L).name(episodeDto.getName()).build());
        verifyNoMoreInteractions(episodeRepository);
    }

    @Test
    void testCreateEpisodeExceptionRollbackWhenCreateOneThenCallSave() {
        final var episodeDto = EpisodeDto.builder().id(1L).name("The eyes see more").build();

        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> episodeService.createEpisodeExceptionRollback(episodeDto));

        verify(episodeRepository, times(1))
                .save(Episode.builder().id(1L).name(episodeDto.getName()).build());
        verifyNoMoreInteractions(episodeRepository);
    }

    @Test
    void testCreateEpisodeExceptionNoRollbackWhenCreateOneThenCallSave() {
        final var episodeDto = EpisodeDto.builder().id(1L).name("The eyes see more").build();

        assertThatExceptionOfType(EpisodeException.class)
                .isThrownBy(() -> episodeService.createEpisodeExceptionNoRollback(episodeDto));

        verify(episodeRepository, times(1))
                .save(Episode.builder().id(1L).name(episodeDto.getName()).build());
    }

    @Test
    void testCreateEpisodeMixRollbackWhenCreateOneThenCallSave() {
        final var episodeDto = EpisodeDto.builder().id(1L).name("The eyes see more").build();

        assertThatExceptionOfType(VideoCountryException.class)
                .isThrownBy(() -> episodeService.createEpisodeMixRollback(episodeDto));

        verify(episodeRepository, times(1))
                .save(Episode.builder().id(1L).name(episodeDto.getName()).build());
    }

    @Test
    void testCreateEpisodeMixNoRollbackWhenCreateOneThenCallSave() {
        final var episodeDto = EpisodeDto.builder().id(1L).name("The eyes see more").build();

        assertThatExceptionOfType(EpisodeException.class)
                .isThrownBy(() -> episodeService.createEpisodeMixNoRollback(episodeDto));

        verify(episodeRepository, times(1))
                .save(Episode.builder().id(1L).name(episodeDto.getName()).build());
    }

    @Test
    void testGetEpisodeByIdWhenGetById1ThenGetEpisode1() {
        final var episodeDto = EpisodeDto.builder().id(1L).name("The eyes see more").build();
        when(episodeRepository.findById(1L)).thenReturn(Optional.of(Episode.builder().id(1L).name(episodeDto.getName()).build()));

        final var resultEpisodeDto = episodeService.getEpisodeById(1L);

        assertThat(resultEpisodeDto).isEqualTo(episodeDto);

    }

    @Test
    void getAllEpisodes() {
        final var episodeDto = EpisodeDto.builder().id(1L).name("The eyes see more").build();
        final var episodes = List.of(Episode.builder().id(1L).name(episodeDto.getName()).build());
        when(episodeRepository.findAll()).thenReturn(episodes);

        final var resultEpisodeDtos = episodeService.getAllEpisodes();

        assertThat(resultEpisodeDtos).isEqualTo(List.of(episodeDto));
        verify(episodeRepository, times(1)).findAll();
        verifyNoMoreInteractions(episodeRepository);
    }

    @Test
    void getAllEpisodesMoreCompleteExampleTest() {
        final var videoName = "Useless videos";
        final var episodeDto = EpisodeDto.builder().id(1L).name(videoName).build();
        final var episodes = List.of(Episode.builder().id(1L).name(episodeDto.getName()).build());
        when(episodeRepository.findAll()).thenReturn(episodes);

        final var resultEpisodeDtos = episodeService.getAllEpisodes();

        assertThat(resultEpisodeDtos).isEqualTo(List.of(episodeDto));
        assertThat(episodeDto.getName()).isEqualTo(videoName);
        verify(episodeRepository, times(1)).findAll();
        verifyNoMoreInteractions(episodeRepository);
    }
}