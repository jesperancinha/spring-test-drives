package org.jesperancinha.sftd.app2.scrap.service;

import org.jesperancinha.sftd.app2.scrap.converter.ScrapbookConverter;
import org.jesperancinha.sftd.app2.scrap.dto.ScrapbookDto;
import org.jesperancinha.sftd.app2.scrap.repository.ScrapbookRepository;

import java.util.List;
import java.util.stream.Collectors;

import static org.jesperancinha.sftd.app2.scrap.converter.ScrapbookConverter.*;

public class ScrapbookClosedService implements ScrapbookService {

    protected final ScrapbookRepository scrapbookRepository;

    public ScrapbookClosedService(ScrapbookRepository scrapbookRepository) {
        this.scrapbookRepository = scrapbookRepository;
    }

    @Override
    public ScrapbookDto createScrapbook(ScrapbookDto scrapbook) {
        return toScrapBookDto(this.scrapbookRepository.save(toScrapbook(scrapbook)));
    }

    @Override
    public ScrapbookDto createScrapbookBadReservations(ScrapbookDto scrapbook) {
        return toScrapBookDto(this.scrapbookRepository.save(toScrapbookBadReservations(scrapbook)));
    }

    @Override
    public ScrapbookDto getBookById(Long id) {
        return toScrapBookDto(this.scrapbookRepository.findById(id).orElse(null));
    }

    @Override
    public ScrapbookDto updateScrapbook(ScrapbookDto scrapbook) {
        return toScrapBookDto(this.scrapbookRepository.save(toScrapbook(scrapbook)));
    }

    @Override
    public void deleteBookById(Long id) {
        this.scrapbookRepository.deleteById(id);
    }

    @Override
    public List<ScrapbookDto> getAllScrapBooks() {
        return this.scrapbookRepository.findAll().stream().map(ScrapbookConverter::toScrapBookDto).collect(Collectors.toList());
    }
}
