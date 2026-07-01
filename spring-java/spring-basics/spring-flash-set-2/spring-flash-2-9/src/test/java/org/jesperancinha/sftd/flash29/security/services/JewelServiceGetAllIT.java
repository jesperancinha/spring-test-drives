package org.jesperancinha.sftd.flash29.security.services;

import org.jesperancinha.sftd.flash29.security.domain.Jewel;
import org.jesperancinha.sftd.flash29.security.dto.JewelDto;
import org.jesperancinha.sftd.flash29.security.repository.JewelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.jesperancinha.sftd.flash29.security.services.JewelType.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;


/**
 * In this case, we also do not use {@link SpringExtension}, because we want security to be activated.
 * When using Spring Extension, some features of Spring like Spring Security, won't be activated, resulting in this particular case, in a test that seems to work both ways, but it only actually tests the security aspect with {@link SpringBootTest}
 */
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = JewelServiceImpl.class)
@SpringBootTest
class JewelServiceGetAllIT {

    @Autowired
    private JewelService jewelService;

    @MockitoBean
    private JewelRepository jewelRepository;

    @BeforeEach
    public void setUp() {
        reset(jewelRepository);
        when(jewelRepository.save(any())).thenAnswer(invocationOnMock -> invocationOnMock.getArguments()[0]);
        final var gregoryJewel = Jewel.builder().jewelType(PEARL).guardian("gregory_kitten").build();
        final var megaKittenJewel = Jewel.builder().jewelType(DIAMOND).guardian("MegaKitten").build();
        final var joaoJewel = Jewel.builder().jewelType(RUBY).guardian("Joao").build();
        final var jewelList = List.of(gregoryJewel, megaKittenJewel, joaoJewel);
        when(jewelRepository.findAll()).thenReturn(jewelList);
    }

    @Test
    void testGetAll_whenNoAuthentication_thenStillListAll() {
        final List<JewelDto> all = jewelService.getAll();

        assertThat(all).isNotNull();
        assertThat(all).hasSize(3);
        final JewelDto gregoryJewel = all.get(0);
        final JewelDto megaKittenJewel = all.get(1);
        final JewelDto joaoJewel = all.get(2);
        assertThat(gregoryJewel).isNotNull();
        assertThat(megaKittenJewel).isNotNull();
        assertThat(joaoJewel).isNotNull();
        assertThat(gregoryJewel.getGuardian()).isEqualTo("gregory_kitten");
        assertThat(gregoryJewel.getJewelType()).isEqualTo(PEARL);
        assertThat(megaKittenJewel.getGuardian()).isEqualTo("MegaKitten");
        assertThat(megaKittenJewel.getJewelType()).isEqualTo(DIAMOND);
        assertThat(joaoJewel.getGuardian()).isEqualTo("Joao");
        assertThat(joaoJewel.getJewelType()).isEqualTo(RUBY);
    }

}