package org.jesperancinha.sftd.app2.scrap.converter;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.jesperancinha.sftd.app2.scrap.dto.ScrapbookDto;
import org.jesperancinha.sftd.app2.scrap.model.Scrapbook;
import org.jesperancinha.sftd.app2.scrap.model.ScrapbookBadreservations;

import java.util.Objects;

import static org.jesperancinha.console.consolerizer.console.ConsolerizerComposer.title;

public class ScrapbookConverter {

    public static ScrapbookDto toScrapBookDto(final Scrapbook scrapBook) {
        ConsolerizerComposer
                .outSpace()
                .green(title("Single responsibility principle (SOLID) - method toScrapBookDto"))
                .blue("The goal of class %s is to perform conversions.", ScrapbookConverter.class.getCanonicalName())
                .blue("This is the first principle of S.O.L.I.D")
                .reset();
        if (Objects.isNull(scrapBook)) {
            return null;
        }
        final ScrapbookDto scrapBookDto = new ScrapbookDto();
        scrapBookDto.setId(scrapBook.getId());
        scrapBookDto.setName(scrapBook.getName());
        scrapBookDto.setScrapbookType(scrapBook.getScrapbookType());
        scrapBookDto.setPages(scrapBook.getPages());
        return scrapBookDto;
    }

    public static Scrapbook toScrapbook(final ScrapbookDto scrapBookDto) {
        ConsolerizerComposer
                .outSpace()
                .green(title("Single responsibility principle (SOLID) - method toScrapbook"))
                .blue("The goal of class %s is to perform conversions.", ScrapbookConverter.class.getCanonicalName())
                .blue("This is the first principle of S.O.L.I.D")
                .reset();
        final Scrapbook scrapbook = new Scrapbook();
        scrapbook.setId(scrapBookDto.getId());
        scrapbook.setName(scrapBookDto.getName());
        scrapbook.setScrapbookType(scrapBookDto.getScrapbookType());
        scrapbook.setPages(scrapBookDto.getPages());
        return scrapbook;
    }

    public static Scrapbook toScrapbookBadReservations(final ScrapbookDto scrapBookDto) {
        final Scrapbook scrapbook = new ScrapbookBadreservations();
        ConsolerizerComposer
                .outSpace()
                .green(title("Liskov Substitution Principle (SOLID) - method toScrapbookBadReservations"))
                .blue("The goal of class %s is to perform conversions.", ScrapbookConverter.class.getCanonicalName())
                .blue("This is the first principle of Liskov substitution")
                .blue("We will save a subclass of Scrapbook called ScrapbookBadReservations")
                .blue(scrapbook.getClass().getCanonicalName())
                .blue("It is a subclass, which means that it will not interfere with the functionalities who use its upper class")
                .reset();
        scrapbook.setId(scrapBookDto.getId());
        scrapbook.setName(scrapBookDto.getName());
        scrapbook.setScrapbookType(scrapBookDto.getScrapbookType());
        scrapbook.setPages(scrapBookDto.getPages());
        return scrapbook;
    }
}
