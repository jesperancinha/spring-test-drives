package org.jesperancinha.sftd.service;

import org.jesperancinha.sftd.config.SpaceRocketConfig;
import org.jesperancinha.sftd.entities.SpaceRocket;
import org.jesperancinha.sftd.entities.SpaceRocketRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpaceRocketConfig.class,
        loader = AnnotationConfigContextLoader.class)
public class SpaceRocketServiceIntegrationTest {

    @Autowired
    SpaceRocketService spaceRocketService;

    @Autowired
    SpaceRocketRepository spaceRocketRepository;


    @BeforeEach
    public void setUp() {
        spaceRocketService.addRocket(SpaceRocket.builder().id(2).name("Ariane1.0").engineType("A62").payLoad(5.0001).height(63).build());
        spaceRocketService.addRocket(SpaceRocket.builder().id(3).name("Ariane1.1").engineType("A64").payLoad(11.0001).height(63).build());
    }

    @AfterEach
    public void tearDown() {
        spaceRocketRepository.deleteAll();
    }

    @Test
    public void getAllRockets() {

        final List<SpaceRocket> allRocketResult = StreamSupport.stream(spaceRocketService.getAllRockets().spliterator(), true).collect(Collectors.toList());
        assertThat(allRocketResult).hasSize(2);

    }

    @Test
    public void deleteRocket() {

        final SpaceRocket spaceRocket = SpaceRocket.builder().id(4).name("Ariane").engineType("A64").payLoad(11.0001).height(63).build();
        spaceRocketRepository.save(spaceRocket);

        Optional<SpaceRocket> rocketResult = spaceRocketRepository.findById(4);

        assertThat(rocketResult).isPresent();
        assertThat(rocketResult.get().getId()).isEqualTo(4);

        spaceRocketService.deleteRocket(4);

        rocketResult = spaceRocketRepository.findById(4);

        assertThat(rocketResult).isEmpty();

    }

    @Test
    public void addRocket() {

        final SpaceRocket spaceRocket = SpaceRocket.builder().id(1).name("Ariane").engineType("A64").payLoad(11.0001).height(63).build();
        spaceRocketService.addRocket(spaceRocket);

        final Optional<SpaceRocket> spaceRocketResult = StreamSupport.stream(spaceRocketService.getAllRockets().spliterator(), true).filter(
                spaceRocket1 -> spaceRocket1.getId() == 1
        ).findFirst();

        assertThat(spaceRocketResult).isPresent();
        final SpaceRocket actualSpaceRocketResult = spaceRocketResult.get();
        assertThat(actualSpaceRocketResult.getName()).isEqualTo("Ariane");
        assertThat(actualSpaceRocketResult.getEngineType()).isEqualTo("A64");
        assertThat(actualSpaceRocketResult.getPayLoad()).isEqualTo(11.0001);
        assertThat(actualSpaceRocketResult.getHeight()).isEqualTo(63.0);

    }

}
