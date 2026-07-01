package org.jesperancinha.sftd.flash16.authority.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static java.util.Collections.singletonList;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(Flash16Controller.class)
class Flash16ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "admin",
            roles = "ADMIN")
    void testGenericHandleWhenPerformingRequestThenResponseOk() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/jsp/userdata.jsp"))
                .andExpect(view().name("userdata"))
                .andExpect(model().attribute("name", "admin"))
                .andExpect(model().attribute("roles", singletonList(
                        new SimpleGrantedAuthority("ROLE_ADMIN"))));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testGenericHandleWhenPerformingRequestThenSuccess() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(forwardedUrl("/WEB-INF/jsp/userdata.jsp"));
    }
}