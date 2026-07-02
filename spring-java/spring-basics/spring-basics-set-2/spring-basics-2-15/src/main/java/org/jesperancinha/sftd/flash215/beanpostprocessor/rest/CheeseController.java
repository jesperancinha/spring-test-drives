package org.jesperancinha.sftd.flash215.beanpostprocessor.rest;

import org.jesperancinha.sftd.flash215.beanpostprocessor.bean.Cheese;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class CheeseController {

    private final Cheese camembert;

    private final Cheese brie;

    private final Cheese saoJorge;

    public CheeseController(
            @Qualifier("camembert")
            Cheese camembert,
            @Qualifier("brie")
            Cheese brie,
            @Qualifier("saoJorge")
            Cheese saoJorge) {
        this.camembert = camembert;
        this.brie = brie;
        this.saoJorge = saoJorge;
    }


    @GetMapping("/")
    public List<Cheese> getAllCheeses() {
        return Arrays.asList(camembert, brie, saoJorge);
    }
}
