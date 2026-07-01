package org.jesperancinha.sftd.mastery3.plants.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import org.jesperancinha.sftd.mastery3.plants.model.Plant;
import org.jetbrains.annotations.NotNull;

@Value
@Builder
@AllArgsConstructor(onConstructor_ = @JsonCreator)
public class PlantDto {

    String name;

    String scientificName;

    public static PlantDto toDto(@NotNull Plant plant) {
        return PlantDto
                .builder()
                .name(plant.getName())
                .scientificName(plant.getScientificName())
                .build();
    }

    public Plant toData() {
        return Plant.builder()
                .name(name)
                .scientificName(scientificName)
                .build();
    }
}
