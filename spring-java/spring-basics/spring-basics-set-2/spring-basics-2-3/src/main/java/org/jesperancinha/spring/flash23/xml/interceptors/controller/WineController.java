package org.jesperancinha.spring.flash23.xml.interceptors.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WineController {

    @GetMapping("/")
    public String getString() {
        return "Fine Wine";
    }
}
