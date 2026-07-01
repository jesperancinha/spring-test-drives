package org.jesperancinha.sftd.mastery3.plants.dao;

import org.jesperancinha.sftd.mastery3.plants.model.Plant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PlantDaoTest {

    @Autowired
    private PlantDao plantDao;

    @Test
    public void testCreatePlantWhenTryingToCreateThenReturnNull() {
        final var plant = plantDao.createPlant(
                Plant.builder()
                        .name("Yucca")
                        .scientificName("Yucca filamentosa")
                        .build());

        assertThat(plant).isNull();
    }

    @Test
    @WithMockUser(username = "Joao")
    public void testGetFilteredList() {
        final var plants = plantDao.getExamplePlants();
        assertThat(plants).hasSize(1);
        assertThat(plants.get(0)).isEqualTo(Plant.builder()
                .name("Yucca")
                .owner("Joao")
                .scientificName("Yucca filamentosa")
                .build());
        assertThat(plants).allMatch(plant -> plant.getOwner().equals("Joao"));
    }
}