package org.jesperancinha.sftd.flash29.security.repository;

import jakarta.transaction.Transactional;
import org.jesperancinha.sftd.flash29.security.domain.Jewel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ContextStartedEvent;

import static jakarta.transaction.Transactional.TxType.REQUIRED;
import static org.assertj.core.api.Assertions.assertThat;
import static org.jesperancinha.sftd.flash29.security.services.JewelType.*;

@DataJpaTest
class JewelRepositoryTest {
    @Autowired
    private JewelRepository jewelRepository;
    @Autowired
    private ConfigurableApplicationContext applicationContext;
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    private Long sabinoId;

    @BeforeEach
    @Transactional
    public void setUp() {
        applicationEventPublisher.publishEvent(new ContextStartedEvent(applicationContext));
        final var jewel = jewelRepository.save(Jewel.builder()
                .id(2L)
                .jewelType(DIAMOND)
                .guardian("sabino").build());

        assertThat(jewel).isNotNull();
        assertThat(jewel.getId()).isNotNull();
        assertThat(jewel.getJewelType()).isEqualTo(DIAMOND);
        assertThat(jewel.getGuardian()).isEqualTo("sabino");

        sabinoId = jewel.getId();
    }

    @Test
    public void testGetJewelWhenReadingJewelThenResultInJewel() {
        final var jewel = jewelRepository.findAll().stream().filter(j -> j.getGuardian().equals("admin")).findFirst().orElse(null);
        final var jewel2 = jewelRepository.getOne(sabinoId);

        assertThat(jewel).isNotNull();
        assertThat(jewel.getGuardian()).isEqualTo("admin");
        assertThat(jewel.getJewelType()).isEqualTo(AMETHYST);
        assertThat(jewel2).isNotNull();
        assertThat(jewel2.getGuardian()).isEqualTo("sabino");
        assertThat(jewel2.getJewelType()).isEqualTo(DIAMOND);
    }

    @Test
    @Transactional(REQUIRED)
    public void testSaveJewelWhenCreatingThenGetId() {
        final Jewel jewel = jewelRepository.save(Jewel.builder()
                .id(3L)
                .jewelType(RUBY)
                .guardian("joao").build());

        assertThat(jewel).isNotNull();
        assertThat(jewel.getId()).isNotNull();
        assertThat(jewel.getJewelType()).isEqualTo(RUBY);
        assertThat(jewel.getGuardian()).isEqualTo("joao");
    }

}