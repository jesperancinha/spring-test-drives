package org.jesperancinha.sftd.flash43.security.context;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class SpringFlash43LauncherTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void testLoginPageWhenNoAuthenticationThenAllow() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk());
    }

    @Test
    void testPasswordEncoderWhenInjectedThenNotNull() {
        assertThat(passwordEncoder).isNotNull();
    }

    @Test
    void testRevealSecretWhenCalledWithoutAuthenticationThenRedirect() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    @WithMockUser(username = "joao",
            roles = "ADMIN")
    void testRevealSecretWhenCalledWithAdminThenForbidden() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "joao",
            roles = "USER")
    void testRevealSecretWhenCalledWithUserThenOk() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    void testRevealPartOfTheSecretWhenCalledWithoutAuthenticationThenRedirect() throws Exception {
        mockMvc.perform(get("/origin"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    @WithMockUser(username = "joao",
            roles = "USER")
    void testRevealPartOfTheSecretWhenCalledWithUserThenForbidden() throws Exception {
        mockMvc.perform(get("/origin"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "joao",
            roles = "ADMIN")
    void testRevealPartOfTheSecretWhenCalledWithAdminThenOk() throws Exception {
        mockMvc.perform(get("/origin"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    void testContext() {
    }
}