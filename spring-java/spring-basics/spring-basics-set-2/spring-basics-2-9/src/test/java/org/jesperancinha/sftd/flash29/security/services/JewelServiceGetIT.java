package org.jesperancinha.sftd.flash29.security.services;

import org.jesperancinha.sftd.flash29.security.domain.Jewel;
import org.jesperancinha.sftd.flash29.security.repository.JewelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Optional;

import static org.jesperancinha.sftd.flash29.security.services.JewelType.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@SpringBootTest
class JewelServiceGetIT {

    @Autowired
    private JewelService jewelService;

    @MockitoBean
    private JewelRepository jewelRepository;

    @BeforeEach
    public void setUp() {
        reset(jewelRepository);
        when(jewelRepository.save(any())).thenAnswer(invocationOnMock -> invocationOnMock.getArguments()[0]);
        final var gregoryJewel = Optional.of(Jewel.builder().jewelType(PEARL).guardian("gregory_kitten").build());
        final var megaKitten = Optional.of(Jewel.builder().jewelType(DIAMOND).guardian("MegaKitten").build());
        final var joao = Optional.of(Jewel.builder().jewelType(RUBY).guardian("Joao").build());
        when(jewelRepository.findById(1L)).thenReturn(gregoryJewel);
        when(jewelRepository.findById(2L)).thenReturn(megaKitten);
        when(jewelRepository.findById(3L)).thenReturn(joao);
    }

    @Test
    void testGetJewelById_whenNoAuthentication_thenFail() {
        assertThrows(IllegalArgumentException.class, () -> jewelService.getJewelById(1L));
    }

    @Test
    @WithMockUser(username = "MegaKitten")
    void testGetJewelById_whenGuardianDoesNotMatch_thenFail() {
        assertThrows(AccessDeniedException.class, () -> jewelService.getJewelById(3L));
    }

    @Test
    @WithMockUser(username = "MegaKitten")
    void testGetJewelById_whenGuardianMatch_thenOk() {
        jewelService.getJewelById(2L);
    }


}