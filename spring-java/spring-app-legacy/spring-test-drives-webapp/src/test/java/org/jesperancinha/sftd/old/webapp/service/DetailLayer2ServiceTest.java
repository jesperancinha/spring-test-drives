package org.jesperancinha.sftd.old.webapp.service;

import org.jesperancinha.sftd.old.webapp.model.Detail;
import org.jesperancinha.sftd.old.webapp.model.DetailEntity;
import org.jesperancinha.sftd.old.webapp.repository.DetailRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class DetailLayer2ServiceTest {

    private static final String NAME_1 = "Name1";
    private static final String CITY_1 = "City1";

    @Autowired
    private DetailLayer2Service detailController;

    @Autowired
    private DetailRepository detailRepository;

    @Autowired
    CacheManager cacheManager;

    @BeforeEach
    public void setUp() {
        detailRepository.deleteAll();
        addElements();
    }

    private void addElements() {
        detailRepository.save(DetailEntity.builder().id(1).name(NAME_1).city(CITY_1).build());
        detailRepository.save(DetailEntity.builder().id(2).name(NAME_1).city(CITY_1).build());
    }

    @Test
    public void findDetailById() {
        testCity(1);
        testCity(2);


        System.out.println(Objects.requireNonNull(Objects.requireNonNull(cacheManager.getCache("detailCache"))
                .get(1)).get());
        System.out.println(Objects.requireNonNull(cacheManager.getCache("detailCache"))
                .get(2));

    }

    private void testCity(int cityNumber) {
        final Detail result = detailController.findDetailById(cityNumber);
        assertThat(result.getName()).isEqualTo(NAME_1);
        assertThat(result.getCity()).isNull();

        detailRepository.deleteAll();

        DetailEntity checkForNone = detailRepository.findById(cityNumber).orElse(null);
        assertThat(checkForNone).isNull();

        final Detail resultCached = detailController.findDetailById(cityNumber);
        assertThat(resultCached.getName()).isEqualTo(NAME_1);
        assertThat(resultCached.getCity()).isNull();

        addElements();

        DetailEntity result2 = detailRepository.findById(cityNumber).orElseThrow();
        assertThat(result2.getName()).isEqualTo(NAME_1);
        assertThat(result2.getCity()).isNull();

        result2.setCity(CITY_1);
        assertThat(result2.getCity()).isEqualTo(CITY_1);

        DetailEntity result3 = detailRepository.findById(cityNumber).orElseThrow();
        assertThat(result3.getName()).isEqualTo(NAME_1);
        assertThat(result3.getCity()).isNull();
    }

}
