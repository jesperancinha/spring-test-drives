package org.jesperancinha.spring.tutorial.persistence.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wine")
public class WineController {

    @GetMapping
    public String getString() {
        return "Fine Wine";
    }
}
