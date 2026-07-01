package org.jesperancinha.sftd.flash415.http.converter.controller;

import org.jesperancinha.sftd.flash415.http.converter.domain.TearDropReport;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class TearDropController {

    @PostMapping(path = "/",
            consumes = APPLICATION_JSON_VALUE)
    public TearDropReport createTearDropReport(
            @RequestBody
            final TearDropReport tearDropReport) {
        return tearDropReport;
    }
}
