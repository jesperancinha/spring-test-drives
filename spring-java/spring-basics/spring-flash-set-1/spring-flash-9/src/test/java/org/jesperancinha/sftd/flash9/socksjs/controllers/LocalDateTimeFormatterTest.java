package org.jesperancinha.sftd.flash9.socksjs.controllers;

import org.junit.jupiter.api.Test;

import java.time.format.DateTimeFormatter;

public class LocalDateTimeFormatterTest {

    @Test
    public void testParser_whenText_thenParseCorrectly() {
        final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss[.SSSSSS][.SSSSS][.SSSS][.SSS][.SS][.S]");
        final var test = "2021-04-21T18:37:29.212795";
        dateTimeFormatter.parse(test);
    }

}
