package org.jesperancinha.sftd.flash33.rollback.transactional.services;

import org.jesperancinha.sftd.flash33.rollback.transactional.domain.Episode;
import org.jesperancinha.sftd.flash33.rollback.transactional.dto.EpisodeDto;
import org.jesperancinha.sftd.flash33.rollback.transactional.exceptions.EpisodeException;
import org.jesperancinha.sftd.flash33.rollback.transactional.exceptions.VideoCountryException;
import org.jesperancinha.sftd.flash33.rollback.transactional.repositories.EpisodeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class EpisodeService {

    private final EpisodeRepository episodeRepository;

    public EpisodeService(EpisodeRepository episodeRepository) {
        this.episodeRepository = episodeRepository;
    }

    @Transactional(rollbackFor = VideoCountryException.class,
            noRollbackFor = EpisodeException.class)
    public void createEpisode(EpisodeDto episodeDto) {
        episodeRepository.save(toData(episodeDto));
        throw new EpisodeException();
    }

    @Transactional(rollbackFor = VideoCountryException.class)
    public void createEpisodeExceptionRollback(EpisodeDto episodeDto) {
        episodeRepository.save(toData(episodeDto));
        throw new VideoCountryException();
    }

    @Transactional(rollbackForClassName = "VideoCountryException",
            noRollbackForClassName = "EpisodeException")
    public void createEpisodeExceptionNoRollback(EpisodeDto episodeDto) {
        episodeRepository.save(toData(episodeDto));
        throw new EpisodeException();

    }

    @Transactional(rollbackFor = VideoCountryException.class,
            noRollbackForClassName = "EpisodeException")
    public void createEpisodeMixRollback(EpisodeDto episodeDto) {
        episodeRepository.save(toData(episodeDto));
        throw new VideoCountryException();
    }

    @Transactional(rollbackFor = VideoCountryException.class,
            noRollbackForClassName = "EpisodeException")
    public void createEpisodeMixNoRollback(EpisodeDto episodeDto) {
        episodeRepository.save(toData(episodeDto));
        throw new EpisodeException();
    }

    public EpisodeDto getEpisodeById(Long id) {
        return toDto(episodeRepository.findById(id).orElse(null));
    }

    public List<EpisodeDto> getAllEpisodes() {
        return episodeRepository
                .findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private EpisodeDto toDto(Episode episode) {
        if (Objects.isNull(episode)) {
            return null;
        }
        return EpisodeDto.builder()
                .id(episode.getId())
                .name(episode.getName())
                .build();
    }

    private Episode toData(EpisodeDto episodeDto) {
        return Episode.builder()
                .id(episodeDto.getId())
                .name(episodeDto.getName())
                .build();
    }
}
