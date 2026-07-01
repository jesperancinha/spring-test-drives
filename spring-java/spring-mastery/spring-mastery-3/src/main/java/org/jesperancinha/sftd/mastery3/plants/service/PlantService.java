package org.jesperancinha.sftd.mastery3.plants.service;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.jesperancinha.sftd.mastery3.plants.dao.PlantDao;
import org.jesperancinha.sftd.mastery3.plants.dto.PlantDto;
import org.jesperancinha.sftd.mastery3.plants.model.Plant;
import org.springframework.stereotype.Service;

@Service
public class PlantService {

    private final PlantDao plantDao;

    public PlantService(PlantDao plantDao) {
        this.plantDao = plantDao;
    }

    public PlantDto createPlant(Plant plant){
        final var plantResult = plantDao.createPlant(plant);
        ConsolerizerComposer.outSpace()
                .magenta("You should no see a plant here. Instead it should be null because we are forcing a rollback")
                .reset();
        return PlantDto.toDto(plantResult);
    }
}
