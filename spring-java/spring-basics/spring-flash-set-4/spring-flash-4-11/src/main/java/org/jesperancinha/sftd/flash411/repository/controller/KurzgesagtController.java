package org.jesperancinha.sftd.flash411.repository.controller;

import org.jesperancinha.sftd.flash411.repository.domain.Kurzgesagt;
import org.jesperancinha.sftd.flash411.repository.service.KurzgesagtService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class KurzgesagtController {

    private final KurzgesagtService kurzgesagtService;

    public KurzgesagtController(KurzgesagtService kurzgesagtService) {
        this.kurzgesagtService = kurzgesagtService;
    }

    @PostMapping("/create")
    public void createKurzgesagt(
            @RequestBody
            final Kurzgesagt kurzgesagt) {
        kurzgesagtService.create(kurzgesagt);
    }

    @GetMapping(path = "/",
            produces = APPLICATION_JSON_VALUE)
    public List<Kurzgesagt> listAll() {
        return kurzgesagtService.getAll();
    }
}
