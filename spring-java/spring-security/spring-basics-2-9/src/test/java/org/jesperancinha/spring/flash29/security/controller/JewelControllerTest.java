package org.jesperancinha.spring.flash29.security.controller;

import org.junit.jupiter.api.parallel.Execution;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import tools.jackson.databind.ObjectMapper;
import org.jesperancinha.spring.flash29.security.configuration.JewelSecurityConfiguration;
import org.jesperancinha.spring.flash29.security.dto.JewelDto;
import org.jesperancinha.spring.flash29.security.repository.JewelRepository;
import org.jesperancinha.spring.flash29.security.services.JewelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.jesperancinha.spring.flash29.security.services.JewelType.EMERALD;
import static org.jesperancinha.spring.flash29.security.services.JewelType.RUBY;
import static org.junit.jupiter.api.parallel.ExecutionMode.SAME_THREAD;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Tests for the controller
 * We are testing all REST methods necessary to manage our jewels
 * Note that for all the non failing cases, we always need minimally one logged-in user at lease, regardless of roles or jewel possession.
 */
@WebMvcTest(controllers = {JewelController.class, JewelSecurityConfiguration.class})
@Execution(SAME_THREAD)
class JewelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @MockitoBean
    private JewelService jewelService;

    @MockitoBean
    private JewelRepository jewelRepository;

    private final ArgumentCaptor<JewelDto> jewelDtoArgumentCaptor = ArgumentCaptor.forClass(JewelDto.class);

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
        reset(jewelRepository);
    }

    @Test
    @WithMockUser(username = "joao", roles = "ADMIN")
    void securityContextIsLoaded() throws Exception {
        final var authentication =
                SecurityContextHolder.getContext().getAuthentication();

        assertThat(authentication).isNotNull();
        assertThat(authentication.isAuthenticated()).isTrue();
        assertThat(authentication.getName()).isEqualTo("joao");
        assertThat(authentication.getAuthorities())
                .extracting(GrantedAuthority::getAuthority)
                .containsExactly("ROLE_ADMIN");

        mockMvc.perform(get("/"))
                .andDo(result -> {
                    System.out.println("STATUS: " +
                            result.getResponse().getStatus());
                    System.out.println("LOCATION: " +
                            result.getResponse().getHeader("Location"));
                    System.out.println("BODY: " +
                            result.getResponse().getContentAsString());
                });
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