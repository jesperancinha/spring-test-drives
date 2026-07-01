package org.jesperancinha.sftd.app2.scrap.controller;

import org.jesperancinha.sftd.app2.scrap.dto.ScrapbookDto;
import org.jesperancinha.sftd.app2.scrap.service.ScrapBookClosedReadService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("api/reader")
public class ScrapbookReaderController {

    private final ScrapBookClosedReadService scrapBookClosedReadService;

    public ScrapbookReaderController(
            final ScrapBookClosedReadService scrapBookClosedReadService) {
        this.scrapBookClosedReadService = scrapBookClosedReadService;
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<ScrapbookDto> getAllScrapBooks() {
        return this.scrapBookClosedReadService.getAllScrapBooks();
    }

    @GetMapping(path = "{name}",
            produces = APPLICATION_JSON_VALUE)
    public List<ScrapbookDto> getScrapbookByName(
            @PathVariable
                    String name) {
        return this.scrapBookClosedReadService.getScrapbooksByName(name);
    }
}
