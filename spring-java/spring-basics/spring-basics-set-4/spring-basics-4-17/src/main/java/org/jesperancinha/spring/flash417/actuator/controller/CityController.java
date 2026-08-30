package org.jesperancinha.spring.flash417.actuator.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CityController {

    @GetMapping("/")
    public String getCity() {
        return "Ghost Town";
    }
}
