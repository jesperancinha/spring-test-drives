package org.jesperancinha.spring.flash29.security.services;

import org.jesperancinha.spring.flash29.security.domain.Jewel;
import org.jesperancinha.spring.flash29.security.dto.JewelDto;
import org.jesperancinha.spring.flash29.security.repository.JewelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.jesperancinha.spring.flash29.security.services.JewelType.PEARL;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class JewelServiceDeleteIT {

    @Autowired
    private JewelService jewelService;

    @MockitoBean
    private JewelRepository jewelRepository;

        private final ArgumentCaptor<Jewel> jewelArgumentCaptor = ArgumentCaptor.forClass(Jewel.class);

    @BeforeEach
    public void setUp() {
        reset(jewelRepository);
    }

    @Test
    void testDeleteJewel_whenCallingWithoutAuthentication_thenFail() {
        final var jewel = JewelDto.builder().jewelType(PEARL).guardian("ThunderKitten").build();
        assertThrows(IllegalArgumentException.class, () -> jewelService.deleteJewel(jewel));
    }

    @Test
    @WithMockUser(username = "ThunderKitten")
    void testDeleteJewel_whenNoRoles_thenFail() {
        final var jewel = JewelDto.builder().jewelType(PEARL).guardian("ThunderKitten").build();

        assertThrows(AccessDeniedException.class, () -> jewelService.deleteJewel(jewel));
    }

    @Test
    @WithMockUser(username = "SuperKitten",
            roles = "ADMIN")
    void testDeleteJewel_whenRolesButNoMatch_thenFail() {
        final var jewel = JewelDto.builder().jewelType(PEARL).guardian("ThunderKitten").build();

        assertThrows(AccessDeniedException.class, () -> jewelService.deleteJewel(jewel));
    }

    @Test
    @WithMockUser(username = "SuperKitten",
            roles = "ADMIN")
    void testDeleteJewel_whenRolesAndMatch_thenOk() {
        final var superKittenJewel = JewelDto.builder().jewelType(PEARL).guardian("SuperKitten").build();

        jewelService.deleteJewel(superKittenJewel);

        verify(jewelRepository, times(1)).delete(jewelArgumentCaptor.capture());
        final Jewel value = jewelArgumentCaptor.getValue();
        assertThat(value).isNotNull();
        assertThat(value.getJewelType()).isEqualTo(PEARL);
        assertThat(value.getGuardian()).isEqualTo("SuperKitten");
    }
}