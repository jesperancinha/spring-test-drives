package org.jesperancinha.stf.flash311.securitymatchers.controllers;

import org.jesperancinha.stf.flash311.securitymatchers.configuration.Flash311ConfigurationAdapter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
@Import(Flash311ConfigurationAdapter.class)
@EnableWebSecurity
class Flash311ControllerITTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "neo_truth",
            roles = "RED")
    void testGenericHandleAdminWhenAdminThenOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin"))
                .andExpect(status().isOk())
                .andExpect(view().name("redpill"))
                .andExpect(model().attribute("name", "neo_truth"))
                .andExpect(model().attribute("roles",
                        List.of(new SimpleGrantedAuthority("ROLE_RED"))));
    }

    @Test
    @WithMockUser(username = "neo_lie",
            roles = "BLUE")
    void testGenericHandleAdminWhenUserThenAuthorizationDenied() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin"))
                .andExpect(status().isForbidden());
    }

    @Test
    void testGenericHandleNormalWhenNoAuthenticationThenStillAuthorized() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/normal"))
                .andExpect(status().isOk())
                .andExpect(view().name("userdata"))
                .andExpect(model().attribute("name", "anonymousUser"))
                .andExpect(model().attribute("roles",
                        List.of(new SimpleGrantedAuthority("ROLE_ANONYMOUS"))));
    }

    @Test
    @WithMockUser(username = "neo_lie",
            roles = "BLUE")
    void testGenericHandleUserWhenUserThenOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user"))
                .andExpect(status().isOk())
                .andExpect(view().name("bluepill"))
                .andExpect(model().attribute("name", "neo_lie"))
                .andExpect(model().attribute("roles",
                        List.of(new SimpleGrantedAuthority("ROLE_BLUE"))));
    }

    @Test
    @WithMockUser(username = "neo_truth",
            roles = "RED")
    void testGenericHandleUserWhenAdminThenAuthorizationDenied() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user"))
                .andExpect(status().isForbidden());
    }
}