package org.jesperancinha.sftd.app2.scrap.service;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.jesperancinha.sftd.app2.scrap.converter.ScrapbookConverter;
import org.jesperancinha.sftd.app2.scrap.dto.ScrapbookDto;
import org.jesperancinha.sftd.app2.scrap.repository.ScrapbookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.jesperancinha.console.consolerizer.console.ConsolerizerComposer.title;

@Service("scrapbookClosedService")
public class ScrapbookEnhancedClosedService extends ScrapbookClosedService {
    public ScrapbookEnhancedClosedService(ScrapbookRepository scrapbookRepository) {
        super(scrapbookRepository);
    }

    public List<ScrapbookDto> getScrapbooksByName(String name) {
        ConsolerizerComposer
                .outSpace()
                .green(title("Open/Closed Principle (SOLID)"))
                .blue("getBookByName is a method not available via the interface. This entity does not change its function")
                .reset();
        return this.scrapbookRepository.findScrapbookByNameRegex(name).stream().map(ScrapbookConverter::toScrapBookDto).collect(Collectors.toList());
    }

}
