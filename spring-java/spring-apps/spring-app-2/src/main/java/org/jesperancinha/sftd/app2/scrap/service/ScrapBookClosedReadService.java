package org.jesperancinha.sftd.app2.scrap.service;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.jesperancinha.sftd.app2.scrap.converter.ScrapbookConverter;
import org.jesperancinha.sftd.app2.scrap.dto.ScrapbookDto;
import org.jesperancinha.sftd.app2.scrap.repository.ScrapbookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.jesperancinha.console.consolerizer.console.ConsolerizerComposer.title;

@Service("scrapBookClosedReadService")
public class ScrapBookClosedReadService implements ScrapbookReadService {

    private final ScrapbookRepository scrapbookRepository;

    public ScrapBookClosedReadService(ScrapbookRepository scrapbookRepository) {
        this.scrapbookRepository = scrapbookRepository;
    }

    @Override
    public ScrapbookDto getBookById(Long id) {
        ConsolerizerComposer
                .outSpace()
                .green(title("Interface Segregation Principle (SOLID)"))
                .blue("getBookById is part of this interface implementation because this interface is about a collection of all read methods")
                .cyan("ScrapBookClosedReadService")
                .reset();
        return ScrapbookConverter.toScrapBookDto(scrapbookRepository.getOne(id));
    }

    @Override
    public List<ScrapbookDto> getAllScrapBooks() {
        ConsolerizerComposer
                .outSpace()
                .green(title("Interface Segregation Principle (SOLID)"))
                .blue("getAllScrapBooks is part of this interface implementation because this interface is about a collection of all read methods")
                .cyan("ScrapBookClosedReadService")
                .reset();
        return scrapbookRepository.findAll().stream()
                .map(ScrapbookConverter::toScrapBookDto).collect(Collectors.toList());
    }

    @Override
    public List<ScrapbookDto> getScrapbooksByName(String name) {
        ConsolerizerComposer
                .outSpace()
                .green(title("Interface Segregation Principle (SOLID)"))
                .blue("getScrapbooksByName is part of this interface implementation because this interface is about a collection of all read methods")
                .cyan("ScrapBookClosedReadService")
                .reset();
        return this.scrapbookRepository.findScrapbookByNameRegex(name).stream().map(ScrapbookConverter::toScrapBookDto).collect(Collectors.toList());
    }
}
