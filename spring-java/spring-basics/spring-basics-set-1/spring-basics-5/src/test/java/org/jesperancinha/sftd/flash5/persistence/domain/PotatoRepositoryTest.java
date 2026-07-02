package org.jesperancinha.sftd.flash5.persistence.domain;

import com.ninjasquad.springmockk.MockkBean;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@MockkBean(PotatoService.class)
class PotatoRepositoryTest {

    @Autowired
    private PotatoRepository potatoRepository;

    @Test
    @Transactional
    public void testSaveWhenNewPotatoThenGetItBack() {
        final var potato = new Potato();
        final Potato potatoSave = potatoRepository.save(potato);

        assertThat(potatoSave).isNotNull();
        assertThat(potatoSave.getId()).isNotNull();
        final Long id = potatoSave.getId();

        final Optional<Potato> optato2 = potatoRepository.findById(id);

        assertThat(optato2.isPresent()).isTrue();
        assertThat(optato2.get().getId()).isEqualTo(id);
    }

}