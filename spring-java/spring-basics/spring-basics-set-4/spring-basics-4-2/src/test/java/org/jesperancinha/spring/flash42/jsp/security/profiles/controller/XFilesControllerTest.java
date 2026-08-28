package org.jesperancinha.spring.flash42.jsp.security.profiles.controller;

import org.jesperancinha.spring.flash42.jsp.security.profiles.configuration.XFilesConfigurationAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
@Import(XFilesConfigurationAdapter.class)
class XFilesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    }

    @Test
    @WithMockUser(username = "joao")
    void testGenericHandleWhenCalledWithUserThenRightModelAndView() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("userdata"))
                .andExpect(model().attribute("name", "joao"))
                .andExpect(model().attribute("roles",
                        Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))));
    }

    @Test
    @WithMockUser(username = "joao",
            roles = "ADMIN")
    void testGenericHandleWhenCalledWithAdminThenRightModelAndView() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("userdata"))
                .andExpect(model().attribute("name", "joao"))
                .andExpect(model().attribute("roles",
                        Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"))));
    }

    @Test
    @WithMockUser(username = "joao",
            roles = "SOMETHING")
    void testGenericHandleWhenCalledWithSomethingThenAuthorizationDenied() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isForbidden());
    }
}