package org.jesperancinha.spring.flash10.error.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.YELLOW;

@RestController
public class ErrorController {

    @GetMapping("/")
    public String getString() {
        YELLOW.printGenericTitleLn("You just reached a method that throws an exception");
        throw new RuntimeException();
    }
}
