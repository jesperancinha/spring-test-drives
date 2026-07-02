package org.jesperancinha.sftd.flash416.mock.mvc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.jesperancinha.console.consolerizer.console.Consolerizer.printRainbowTitleLn;
import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

@RestController
public class VampireController {

    @GetMapping(path = "/arms",
            produces = TEXT_PLAIN_VALUE)
    public String arms() {
        printRainbowTitleLn("You've reached arms endpoint!");
        return "Come into these arms again";
    }

    @GetMapping(path = "/body",
            produces = TEXT_PLAIN_VALUE)
    public String body() {
        printRainbowTitleLn("You've reached body endpoint!");
        return "Lay your body down";
    }
}
