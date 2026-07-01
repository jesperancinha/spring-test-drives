package org.jesperancinha.sftd.flash17.cipher.controllers;

import com.ninjasquad.springmockk.MockkBean;
import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.jesperancinha.sftd.flash17.cipher.configuration.Flash17CSRFConfigurationAdapter;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@WebMvcTest({Flash17CSRFCreateController.class, Flash17Controller.class})
@MockkBean(classes = {DataSource.class})
@AutoConfigureMockMvc
@Import(Flash17CSRFConfigurationAdapter.class)
class Flash17CSRFCreateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private JdbcUserDetailsManager jdbcUserDetailsManager;

    @Captor
    private ArgumentCaptor<UserDetails> argumentCaptor;

    @Captor
    private ArgumentCaptor<String> passwordCaptor;

    @SpyBean(BCryptPasswordEncoder.class)
    private PasswordEncoder passwordEncoder;

    @Test
    @WithMockUser(roles = "ADMIN")
    void testCreateUserViaGetWhenCreateUserViaGetThenOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/open/create/admin/password/ADMIN"))
                .andExpect(status().isOk());

        verify(jdbcUserDetailsManager, times(1)).createUser(argumentCaptor.capture());
        verify(passwordEncoder, times(1)).encode(passwordCaptor.capture());
        final var value = argumentCaptor.getValue();
        assertThat(value.getUsername()).isEqualTo("admin");
        assertThat(value.getPassword()).isNotNull();
        assertThat(value.getPassword()).isNotEqualTo("password");
        assertThat(passwordCaptor.getValue()).isEqualTo("password");
        assertThat(value.getAuthorities()).hasSize(1);
        assertThat(value.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))).isTrue();
    }

    @Test
    void testCreateUserViaGeWhenCreateUserViaPostThenForbidden() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/open/create/admin/password/ADMIN"))
                .andExpect(status().isForbidden());
        ConsolerizerComposer.outSpace()
                .green("Take note that this is actually how we should create users")
                .yellow("However, we have CSRF enabled in TST and this is why we cannot POST")
                .reset();
    }
}