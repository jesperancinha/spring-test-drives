package org.jesperancinha.spring.service;

import org.jesperancinha.spring.config.SpaceRocketConfig;
import org.jesperancinha.spring.entities.SpaceRocket;
import org.jesperancinha.spring.entities.SpaceRocketRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpaceRocketConfig.class, SpaceRocketRepository.class},
        loader = AnnotationConfigContextLoader.class)
@Transactional
public class SpaceRocketServiceIntegrationTest {

    @Autowired
    SpaceRocketService spaceRocketService;

    @Autowired
    SpaceRocketRepository spaceRocketRepository;


    @BeforeEach
    public void setUp() {
        spaceRocketRepository.deleteAll();
        spaceRocketService.addRocket(SpaceRocket.builder().name("Ariane1.0").engineType("A62").payLoad(5.0001).height(63).build());
        spaceRocketService.addRocket(SpaceRocket.builder().name("Ariane1.1").engineType("A64").payLoad(11.0001).height(63).build());
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

        SpaceRocket spaceRocket = spaceRocketRepository.save(SpaceRocket.builder().name("Ariane").engineType("A64").payLoad(11.0001).height(63).build());

        Optional<SpaceRocket> rocketResult = spaceRocketRepository.findById(spaceRocket.getId());

        assertThat(rocketResult).isPresent();
        assertThat(rocketResult.get().getId()).isEqualTo(spaceRocket.getId());

        spaceRocketService.deleteRocket(spaceRocket.getId());

        rocketResult = spaceRocketRepository.findById(spaceRocket.getId());

        assertThat(rocketResult).isEmpty();

    }

    @Test
    public void addRocket() {
        SpaceRocket spaceRocket = spaceRocketService.addRocket(SpaceRocket.builder().name("Ariane").engineType("A64").payLoad(11.0001).height(63).build());

        final Optional<SpaceRocket> spaceRocketResult = StreamSupport.stream(spaceRocketService.getAllRockets().spliterator(), true).filter(
                spaceRocket1 -> spaceRocket1.getId().equals(spaceRocket.getId())
        ).findFirst();

        assertThat(spaceRocketResult).isPresent();
        final SpaceRocket actualSpaceRocketResult = spaceRocketResult.get();
        assertThat(actualSpaceRocketResult.getName()).isEqualTo("Ariane");
        assertThat(actualSpaceRocketResult.getEngineType()).isEqualTo("A64");
        assertThat(actualSpaceRocketResult.getPayLoad()).isEqualTo(11.0001);
        assertThat(actualSpaceRocketResult.getHeight()).isEqualTo(63.0);

    }

}
