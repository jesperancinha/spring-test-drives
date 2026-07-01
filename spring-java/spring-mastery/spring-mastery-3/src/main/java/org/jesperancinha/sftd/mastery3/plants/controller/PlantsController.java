package org.jesperancinha.sftd.mastery3.plants.controller;

import org.jesperancinha.sftd.mastery3.plants.dto.PlantDto;
import org.jesperancinha.sftd.mastery3.plants.service.PlantService;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/")
public class PlantsController {

    private final PlantService plantService;

    private final PlantDto yucca;
    private final PlantDto yohoo;
    private final PlantDto yuppi;

    private final PlantDto[] allPlants;

    public PlantsController(PlantService plantService, PlantDto yucca, PlantDto yohoo, PlantDto yuppi, PlantDto[] allPlants) {
        this.plantService = plantService;
        this.yucca = yucca;
        this.yohoo = yohoo;
        this.yuppi = yuppi;
        this.allPlants = allPlants;
    }

    @PostMapping(path = "/create/old",
            consumes = APPLICATION_JSON_VALUE)
    public PlantDto openPostFail(
            @RequestBody
            final PlantDto plantDto) {
        return plantService.createPlant(plantDto.toData());
    }

    @GetMapping(path = "/list/copies", produces = APPLICATION_JSON_VALUE)
    public List<PlantDto> get3Copies() {
        return List.of(yucca, yohoo, yuppi);
    }
    @GetMapping(path = "/list/array/copies", produces = APPLICATION_JSON_VALUE)
    public List<PlantDto> get3CopiesArray() {
        return Arrays.stream(allPlants).collect(Collectors.toList());
    }
}
