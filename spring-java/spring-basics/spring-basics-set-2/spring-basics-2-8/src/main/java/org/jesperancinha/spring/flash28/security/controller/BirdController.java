package org.jesperancinha.spring.flash28.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BirdController {

    @GetMapping("/")
    public String getBird() {
        return "A little bird lit down on henry lee";
    }
}
