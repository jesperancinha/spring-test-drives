package org.jesperancinha.sftd.app2.scrap.service;

import org.jesperancinha.sftd.app2.scrap.dto.ScrapbookDto;

import java.util.List;

public interface ScrapbookService {

    ScrapbookDto createScrapbook(final ScrapbookDto scrapbook);

    ScrapbookDto createScrapbookBadReservations(final ScrapbookDto scrapbook);

    ScrapbookDto getBookById(final Long id);

    ScrapbookDto updateScrapbook(ScrapbookDto scrapbook);

    void deleteBookById(final Long id);

    List<ScrapbookDto> getAllScrapBooks();
}
