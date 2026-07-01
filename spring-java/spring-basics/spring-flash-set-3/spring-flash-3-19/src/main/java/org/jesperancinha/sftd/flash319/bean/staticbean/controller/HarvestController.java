package org.jesperancinha.sftd.flash319.bean.staticbean.controller;

import org.jesperancinha.sftd.flash319.bean.staticbean.model.Harvest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class HarvestController {

    @Autowired
    private static Harvest harvest1;

    @Autowired
    private static Harvest harvest2;

    @GetMapping(path = "/",
            produces = APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<Harvest> allHarvests() {
        return Arrays.asList(harvest1, harvest2);
    }
}
