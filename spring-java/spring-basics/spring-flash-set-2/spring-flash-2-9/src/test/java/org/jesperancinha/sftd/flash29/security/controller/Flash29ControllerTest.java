package org.jesperancinha.sftd.flash29.security.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jesperancinha.sftd.flash29.security.configuration.Flash29ConfigurationAdapter;
import org.jesperancinha.sftd.flash29.security.dto.JewelDto;
import org.jesperancinha.sftd.flash29.security.repository.JewelRepository;
import org.jesperancinha.sftd.flash29.security.services.JewelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.jesperancinha.sftd.flash29.security.services.JewelType.EMERALD;
import static org.jesperancinha.sftd.flash29.security.services.JewelType.RUBY;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Tests for the controller
 * We are testing all REST methods necessary to manage our jewels
 * Note that for all the non failing cases, we always need minimally one logged-in user at lease, regardless of roles or jewel possession.
 */
@WebMvcTest(controllers = Flash29Controller.class)
@Import(Flash29ConfigurationAdapter.class)
class Flash29ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private JewelService jewelService;

    @MockitoBean
    private JewelRepository jewelRepository;

    @Captor
    private ArgumentCaptor<JewelDto> jewelDtoArgumentCaptor;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        reset(jewelRepository);
    }

    @Test
    @WithMockUser(username = "joao",
            roles = "ADMIN")
    void testGenericHandleWhenCalledThenReturnOwnedJewelsView() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("ownedjewels"))
                .andExpect(model().attribute("name", "joao"))
                .andExpect(model().attribute("roles", singletonList(
                        new SimpleGrantedAuthority("ROLE_ADMIN"))));

        verifyNoInteractions(jewelRepository);
    }

    @Test
    @WithMockUser(username = "joao",
            roles = "ADMIN")
    void testListJewelsWhenSimpleAuthenticationThenStillAbleToGetList() throws Exception {
        final var listOfJewels = List.of(
                JewelDto.builder().jewelType(EMERALD).guardian("KittenPowers").build(),
                JewelDto.builder().jewelType(RUBY).guardian("KittenStrongSword").build()
        );

        when(jewelService.getAll()).thenReturn(listOfJewels);

        mockMvc.perform(get("/jewels"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(listOfJewels)));

        verify(jewelService, only()).getAll();
        verifyNoInteractions(jewelRepository);

    }

    @Test
    @WithMockUser(username = "joao",
            roles = "ADMIN")
    void testCreateJewelWhenCreatingOneThenCreateIt() throws Exception {
        final var kittenPowersJewel = JewelDto.builder().jewelType(EMERALD).guardian("KittenPowers").build();
        when(jewelService.createJewel(kittenPowersJewel)).thenReturn(kittenPowersJewel);

        mockMvc.perform(post("/jewels")
                .content(objectMapper.writeValueAsString(kittenPowersJewel))
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(kittenPowersJewel)));

        verify(jewelService, only()).createJewel(kittenPowersJewel);
        verifyNoInteractions(jewelRepository);

    }

    @Test
    @WithMockUser(username = "joao",
            roles = "ADMIN")
    void testJewelWhenFetchingByIdThenReturnMatchingJewel() throws Exception {
        final JewelDto jewelDto = JewelDto.builder().jewelType(EMERALD).guardian("KittenPowers").build();
        when(jewelService.getJewelById(1L)).thenReturn(jewelDto);

        mockMvc.perform(get("/jewels/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(jewelDto)));

        verify(jewelService, only()).getJewelById(1L);
        verifyNoInteractions(jewelRepository);
    }

    @Test
    @WithMockUser(username = "joao",
            roles = "ADMIN")
    void testRemoveJewelWhenCallToDeleteJewel1ThenRemoveIt() throws Exception {
        final var jewelDto = JewelDto.builder().jewelType(EMERALD).guardian("KittenPowers").build();
        when(jewelService.getJewelById(1L)).thenReturn(jewelDto);

        mockMvc.perform(delete("/jewels/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));

        verify(jewelService, times(1)).getJewelById(1L);
        verify(jewelService, times(1)).deleteJewel(jewelDtoArgumentCaptor.capture());
        final var deletedJewel = jewelDtoArgumentCaptor.getValue();
        assertThat(deletedJewel).isEqualTo(jewelDto);
        verifyNoInteractions(jewelRepository);
    }
}