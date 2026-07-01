package org.jesperancinha.sftd.flash29.security.services;

import org.jesperancinha.sftd.flash29.security.domain.Jewel;
import org.jesperancinha.sftd.flash29.security.dto.JewelDto;
import org.jesperancinha.sftd.flash29.security.repository.JewelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.jesperancinha.sftd.flash29.security.services.JewelType.OPAL;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class JewelServiceUpdateIT {

    @Autowired
    private JewelService jewelService;

    @MockitoBean
    private JewelRepository jewelRepository;

    @BeforeEach
    public void setUp() {
        reset(jewelRepository);
        when(jewelRepository.save(any())).thenAnswer(invocationOnMock -> invocationOnMock.getArguments()[0]);
    }

    @Captor
    private ArgumentCaptor<Jewel> jewelArgumentCaptor;

    @Test
    void testUpdateJewel_whenNoAuthentication_thenFail() {
        final var jewel = JewelDto
                .builder()
                .jewelType(OPAL)
                .guardian("Kitten")
                .build();

        assertThrows(AuthenticationCredentialsNotFoundException.class, () -> jewelService.updateJewel(jewel));
        verifyNoInteractions(jewelRepository);
    }

    @Test
    @WithMockUser(username = "MegaKitten")
    void testUpdateJewel_whenAuthenticationNoRoles_thenFail() {
        final var jewel = JewelDto
                .builder()
                .jewelType(OPAL)
                .guardian("Kitten")
                .build();

        assertThrows(AccessDeniedException.class, () -> jewelService.updateJewel(jewel));
        verifyNoInteractions(jewelRepository);
    }

    @Test
    @WithMockUser(username = "MegaKitten",
            roles = "ADMIN")
    void testUpdateJewel_whenAuthenticationRolesGuardianUnMatch_thenFail() {
        final var jewel = JewelDto
                .builder()
                .jewelType(OPAL)
                .guardian("Kitten")
                .build();

        assertThrows(AccessDeniedException.class, () -> jewelService.updateJewel(jewel));
        verifyNoInteractions(jewelRepository);
    }

    @Test
    @WithMockUser(username = "MegaKitten",
            roles = "ADMIN")
    void testUpdateJewel_whenAuthenticationRolesGuardiaMatch_thenOk() {
        final var jewel = JewelDto
                .builder()
                .jewelType(OPAL)
                .guardian("MegaKitten")
                .build();

        final var jewelResult = jewelService.updateJewel(jewel);
        assertThat(jewelResult.getJewelType()).isEqualTo(OPAL);
        assertThat(jewelResult.getGuardian()).isEqualTo("MegaKitten");
        verify(jewelRepository, times(1)).save(jewelArgumentCaptor.capture());
        final Jewel result = jewelArgumentCaptor.getValue();
        assertThat(result).isNotNull();
        assertThat(result.getGuardian()).isEqualTo(jewel.getGuardian());
        assertThat(result.getJewelType()).isEqualTo(jewel.getJewelType());
    }
}