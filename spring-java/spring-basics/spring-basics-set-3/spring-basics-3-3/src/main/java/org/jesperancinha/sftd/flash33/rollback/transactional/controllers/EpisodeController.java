package org.jesperancinha.sftd.flash33.rollback.transactional.controllers;

import org.jesperancinha.sftd.flash33.rollback.transactional.dto.EpisodeDto;
import org.jesperancinha.sftd.flash33.rollback.transactional.exceptions.EpisodeException;
import org.jesperancinha.sftd.flash33.rollback.transactional.services.EpisodeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.YELLOW;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class EpisodeController {

    private final EpisodeService episodeService;

    public EpisodeController(EpisodeService episodeService) {
        this.episodeService = episodeService;
    }

    @PostMapping(path = "/createEpisode",
            consumes = APPLICATION_JSON_VALUE)
    public void createEpisode(
            @RequestBody
                    EpisodeDto episode) {
        try {
            episodeService.createEpisode(episode);
        } catch (EpisodeException episodeException) {
            YELLOW.printGenericLn("This exception won't trigger a rollback, please check the main listing page in path /", episodeException);
            throw episodeException;
        }
    }

    @PostMapping(path = "/createEpisodeExceptionRollback",
            consumes = APPLICATION_JSON_VALUE)
    public void createEpisodeExceptionRollback(
            @RequestBody
                    EpisodeDto episode) {
        try {
            episodeService.createEpisodeExceptionRollback(episode);
        } catch (RuntimeException exception) {
            YELLOW.printGenericLn("This exception will trigger a rollback, please check the main listing page in path /", exception);
            throw exception;
        }
    }

    @PostMapping(path = "/createEpisodeExceptionNoRollback",
            consumes = APPLICATION_JSON_VALUE)
    public void createEpisodeExceptionNoRollback(
            @RequestBody
                    EpisodeDto episode) {
        try {
            episodeService.createEpisodeExceptionNoRollback(episode);
        } catch (RuntimeException exception) {
            YELLOW.printGenericLn("This exception will trigger a rollback, please check the main listing page in path /", exception);
            throw exception;
        }
    }

    @PostMapping(path = "/createEpisodeMixRollback",
            consumes = APPLICATION_JSON_VALUE)
    public void createEpisodeMixRollback(
            @RequestBody
                    EpisodeDto episode) {
        try {
            episodeService.createEpisodeMixRollback(episode);
        } catch (RuntimeException exception) {
            YELLOW.printGenericLn("This exception will trigger a rollback, please check the main listing page in path /", exception);
            throw exception;
        }
    }

    @PostMapping(path = "/createEpisodeMixNoRollback",
            consumes = APPLICATION_JSON_VALUE)
    public void createEpisodeMixNoRollback(
            @RequestBody
                    EpisodeDto episode) {
        try {
            episodeService.createEpisodeMixNoRollback(episode);
        } catch (EpisodeException exception) {
            YELLOW.printGenericLn("This exception won't trigger a rollback, please check the main listing page in path /", exception);
        }
    }

    @GetMapping("{id}")
    public EpisodeDto getEpisodeById(
            @PathVariable("id")
                    Long id) {
        return episodeService.getEpisodeById(id);
    }

    @GetMapping("/")
    public List<EpisodeDto> getAllEpisodes() {
        return episodeService.getAllEpisodes();
    }
}
