package org.jesperancinha.spring.old.webapp.service;

import org.jesperancinha.spring.old.webapp.model.Detail;
import org.jesperancinha.spring.old.webapp.model.DetailEntity;
import org.jesperancinha.spring.old.webapp.repository.DetailRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    List<DetailEntity> elements;

    @BeforeEach
    @Transactional
    public void setUp() {
        detailRepository.deleteAll();
        elements = addElements();
    }

    private List<DetailEntity> addElements() {
        return List.of(
                detailRepository.save(DetailEntity.builder().name(NAME_1).city(CITY_1).build()),
                detailRepository.save(DetailEntity.builder().name(NAME_1).city(CITY_1).build())
        );
    }

    @Test
    public void findDetailById() {
        testCity(elements.getFirst().getId());
        testCity(elements.getLast().getId());


        System.out.println(Objects.requireNonNull(Objects.requireNonNull(cacheManager.getCache("detailCache"))
                .get(1)).get());
        System.out.println(Objects.requireNonNull(cacheManager.getCache("detailCache"))
                .get(2));

    }

    private void testCity(int id) {
        final Detail result = detailController.findDetailById(id);
        assertThat(result.getName()).isEqualTo(NAME_1);
        assertThat(result.getCity()).isNull();

        detailRepository.deleteAll();

        DetailEntity checkForNone = detailRepository.findById(id).orElse(null);
        assertThat(checkForNone).isNull();

        final Detail resultCached = detailController.findDetailById(id);
        assertThat(resultCached.getName()).isEqualTo(NAME_1);
        assertThat(resultCached.getCity()).isNull();

        elements = addElements();

        Integer id2 = elements.getFirst().getId();
        DetailEntity result2 = detailRepository.findById(id2).orElseThrow();
        assertThat(result2.getName()).isEqualTo(NAME_1);
        assertThat(result2.getCity()).isNull();

        result2.setCity(CITY_1);
        assertThat(result2.getCity()).isEqualTo(CITY_1);

        DetailEntity result3 = detailRepository.findById(id2).orElseThrow();
        assertThat(result3.getName()).isEqualTo(NAME_1);
        assertThat(result3.getCity()).isNull();
    }

}
