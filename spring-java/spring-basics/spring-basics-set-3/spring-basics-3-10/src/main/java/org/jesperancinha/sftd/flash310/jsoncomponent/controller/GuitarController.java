package org.jesperancinha.sftd.flash310.jsoncomponent.controller;

import org.jesperancinha.sftd.flash310.jsoncomponent.dto.Guitar;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.MAGENTA;

@RestController
public class GuitarController {

    @GetMapping(path = "/",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Guitar> allGuitars() {
        return List.of(
                new Guitar("Fender", "75TH Anni Com Strat MN 2BB", 2400L, "EUR"),
                new Guitar("Jackson", "Jackson JS Series Dinky JS32Q DKA Trans Purple Burst", 500L, "EUR")
        );
    }

    @PostMapping(path = "/")
    public void createGuitar(
            @RequestBody
            final Guitar guitar) {
        MAGENTA.printGenericLn("You've created guitar -> %s", guitar);
    }
}
