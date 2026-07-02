package org.jesperancinha.sftd.flash21.embedded.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("message")
public class SpringFlash21Controller {
    @GetMapping("dancing")
    public String getMessage() {
        return "I keep dancing on my own";
    }
}

