package org.jesperancinha.sftd.app2.scrap.service;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.jesperancinha.sftd.app2.scrap.dto.ScrapbookDto;
import org.jesperancinha.sftd.app2.scrap.model.Scrapbook;
import org.jesperancinha.sftd.app2.scrap.repository.ScrapbookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.jesperancinha.console.consolerizer.console.ConsolerizerComposer.title;
import static org.jesperancinha.sftd.app2.scrap.converter.ScrapbookConverter.*;

@Service("scrapbookFakeService")
public class ScrapbookFakeService implements ScrapbookService {

    private final ScrapbookRepository scrapbookRepository;

    public ScrapbookFakeService(ScrapbookRepository scrapbookRepository) {
        this.scrapbookRepository = scrapbookRepository;
    }

    @Override
    public ScrapbookDto createScrapbook(final ScrapbookDto scrapBookDto) {
        final Scrapbook scrapbook = toScrapbook(scrapBookDto);
        scrapbook.setId((long) (Math.random() * 1000L));
        ConsolerizerComposer.outSpace()
                .blue(title("We create fake Scrapbook with id %d and don't put it through to any persistence backup system", scrapbook.getId()))
                .yellow(scrapBookDto)
                .reset();
        return toScrapBookDto(scrapbook);
    }

    @Override
    public ScrapbookDto createScrapbookBadReservations(ScrapbookDto scrapbookDto) {
        final Scrapbook scrapbook = toScrapbookBadReservations(scrapbookDto);
        scrapbook.setId((long) (Math.random() * 1000L));
        ConsolerizerComposer.outSpace()
                .blue(title("We create fake ScrapbookBadreservations with id %d and don't put it through to any persistence backup system", scrapbook.getId()))
                .yellow(scrapbookDto)
                .reset();
        return toScrapBookDto(scrapbook);
    }

    @Override
    public ScrapbookDto getBookById(Long id) {
        final Scrapbook scrapbook = new Scrapbook();
        scrapbook.setId((long) (Math.random() * 1000L));
        ConsolerizerComposer.outSpace()
                .blue(title("We create a fake Scrap book with id %d and no data and give it back.", scrapbook.getId()))
                .reset();
        return toScrapBookDto(scrapbook);
    }

    @Override
    public ScrapbookDto updateScrapbook(ScrapbookDto scrapbook) {
        ConsolerizerComposer.outSpace()
                .blue(title(" Since our scrapbook already has id %d we just give it back as if the update had succeeded", scrapbook.getId()))
                .yellow(scrapbook)
                .reset();
        return scrapbook;
    }

    @Override
    public void deleteBookById(Long id) {
        ConsolerizerComposer.outSpace()
                .blue(title("We don't delete any scrapbook with id %id. We simply just return as if it had happened"))
                .reset();

    }

    @Override
    public List<ScrapbookDto> getAllScrapBooks() {
        final ScrapbookDto scrapbookDto = new ScrapbookDto();
        scrapbookDto.setId((long) (Math.random() * 1000L));
        return List.of(scrapbookDto);
    }
}
