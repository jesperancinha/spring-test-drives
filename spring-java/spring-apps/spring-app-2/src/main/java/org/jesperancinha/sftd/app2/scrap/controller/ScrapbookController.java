package org.jesperancinha.sftd.app2.scrap.controller;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.jesperancinha.sftd.app2.scrap.dto.ScrapbookDto;
import org.jesperancinha.sftd.app2.scrap.service.ScrapbookEnhancedClosedService;
import org.jesperancinha.sftd.app2.scrap.service.ScrapbookService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.jesperancinha.console.consolerizer.console.ConsolerizerComposer.title;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("api")
public class ScrapbookController {

    private final ScrapbookService scrapbookService3;

    private final List<ScrapbookService> serviceList = new ArrayList<>();

    public ScrapbookController(
            @Qualifier("scrapbookFakeService")
                    ScrapbookService scrapbookService1,
            @Qualifier("scrapBookOpenService")
                    ScrapbookService scrapbookService2,
            @Qualifier("scrapbookClosedService")
                    ScrapbookService scrapbookService3) {
        ConsolerizerComposer
                .outSpace()
                .green(title("Dependency Injection (Inversion Of Control) Principle - (SOLID)"))
                .blue("The services we are injecting are being done so via stereotyping java classes.")
                .blue("This way, beans are created, which can be injected in other beans/configurations.")
                .reset();
        this.scrapbookService3 = scrapbookService3;
        serviceList.add(scrapbookService1);
        serviceList.add(scrapbookService2);
        serviceList.add(scrapbookService3);
    }

    @PostMapping(value = "create",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public ScrapbookDto createScrapBook(
            @RequestBody
            final ScrapbookDto scrapBookDto) {

        return this.serviceList.get((int) (this.serviceList.size() * Math.random())).createScrapbook(scrapBookDto);
    }

    @PostMapping(value = "create/bad",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public ScrapbookDto createScrapBookBadReservations(
            @RequestBody
            final ScrapbookDto scrapBookDto) {
        return this.serviceList.get((int) (this.serviceList.size() * Math.random())).createScrapbookBadReservations(scrapBookDto);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<ScrapbookDto> getAllScrapBooks() {
        return this.serviceList.get((int) (this.serviceList.size() * Math.random())).getAllScrapBooks();
    }

    @GetMapping(path = "{name}",
            produces = APPLICATION_JSON_VALUE)
    public List<ScrapbookDto> getScrapbookByName(
            @PathVariable
                    String name) {
        return ((ScrapbookEnhancedClosedService) this.scrapbookService3).getScrapbooksByName(name);
    }
}
