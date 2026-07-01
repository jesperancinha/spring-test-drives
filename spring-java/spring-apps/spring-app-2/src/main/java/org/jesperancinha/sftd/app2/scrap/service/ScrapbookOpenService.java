package org.jesperancinha.sftd.app2.scrap.service;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.jesperancinha.sftd.app2.scrap.converter.ScrapbookConverter;
import org.jesperancinha.sftd.app2.scrap.dto.ScrapbookDto;
import org.jesperancinha.sftd.app2.scrap.repository.ScrapbookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.jesperancinha.sftd.app2.scrap.converter.ScrapbookConverter.*;

@Service("scrapBookOpenService")
public class ScrapbookOpenService implements ScrapbookService {

    private final ScrapbookRepository scrapbookRepository;

    public ScrapbookOpenService(ScrapbookRepository scrapbookRepository) {
        this.scrapbookRepository = scrapbookRepository;
    }

    @Override
    public ScrapbookDto createScrapbook(ScrapbookDto scrapbook) {
        ConsolerizerComposer.outSpace()
                .blue("You are in the ScrapbookOpenService")
                .blue("This means that the logs are all visible")
                .yellow("You are now creating book")
                .yellow(scrapbook)
                .reset();
        return toScrapBookDto(this.scrapbookRepository.save(toScrapbook(scrapbook)));
    }

    @Override
    public ScrapbookDto createScrapbookBadReservations(ScrapbookDto scrapbook) {
        ConsolerizerComposer.outSpace()
                .blue("You are in the ScrapbookOpenService")
                .blue("This means that the logs are all visible")
                .yellow("You are now creating book")
                .yellow(scrapbook)
                .reset();
        return toScrapBookDto(this.scrapbookRepository.save(toScrapbookBadReservations(scrapbook)));
    }

    @Override
    public ScrapbookDto getBookById(Long id) {
        ConsolerizerComposer.outSpace()
                .blue("You are in the ScrapbookOpenService")
                .blue("This means that the logs are all visible")
                .yellow("You are reading book with id %d", id)
                .reset();
        return toScrapBookDto(this.scrapbookRepository.findById(id).orElse(null));
    }

    @Override
    public ScrapbookDto updateScrapbook(ScrapbookDto scrapbook) {
        ConsolerizerComposer.outSpace()
                .blue("You are in the ScrapbookOpenService")
                .blue("This means that the logs are all visible")
                .yellow("You are now updating book")
                .yellow(scrapbook)
                .reset();
        return toScrapBookDto(this.scrapbookRepository.save(toScrapbook(scrapbook)));
    }

    @Override
    public void deleteBookById(Long id) {
        ConsolerizerComposer.outSpace()
                .blue("You are in the ScrapbookOpenService")
                .blue("This means that the logs are all visible")
                .yellow("You are now deleting book with id %d", id)
                .reset();
        this.scrapbookRepository.deleteById(id);
    }

    @Override
    public List<ScrapbookDto> getAllScrapBooks() {
        ConsolerizerComposer.outSpace()
                .blue("You are in the ScrapbookOpenService")
                .blue("This means that the logs are all visible")
                .yellow("Getting all scrapbooks!")
                .reset();
        return this.scrapbookRepository.findAll().stream().map(ScrapbookConverter::toScrapBookDto).collect(Collectors.toList());
    }
}
