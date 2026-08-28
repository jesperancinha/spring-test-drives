package org.jesperancinha.spring.tutorial.persistence.rest.controller;

import org.jesperancinha.spring.tutorial.persistence.rest.domain.Potato;
import org.jesperancinha.spring.tutorial.persistence.rest.service.PotatoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("potatoes")
public class PotatoController {
    private final PotatoService potatoService;

    public PotatoController(final PotatoService potatoService) {
        this.potatoService = potatoService;
    }

    @PostMapping("/")
    public void createAllPotatoes() {
        var potato1 = new Potato();
        potato1.setForm("Elephant");
        potato1.setLocalDateTime(LocalDateTime.now());
        var potato2 = new Potato();
        potato2.setForm("Cat");
        potato2.setLocalDateTime(LocalDateTime.now());
        var potato3 = new Potato();
        potato3.setForm("Giraffe");
        potato3.setLocalDateTime(LocalDateTime.now());
        potatoService.createPotato(potato1);
        potatoService.createPotato(potato2);
        potatoService.createPotato(potato3);
    }

    @GetMapping("/")
    public List<Potato> getAllPotatoes() {
        return potatoService.getAllPotatoes();
    }
}
