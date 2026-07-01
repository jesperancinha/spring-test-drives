package org.jesperancinha.sftd.flash6.session;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(controllers = SpringFlash6Launcher.class)
class SpringFlash6LauncherTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testShowSessionDetailsWhenCalledThenTopListWithNumbers() throws Exception {
        final ObjectMapper objectMapper = new ObjectMapper();
        final MvcResult mvcResult = mockMvc.perform(get("/"))
                .andReturn();

        assertThat(mvcResult).isNotNull();
        final String contentAsString = mvcResult.getResponse().getContentAsString();
        final List<Long> longs = objectMapper.readValue(contentAsString, new TypeReference<>() {
        });
        assertThat(longs).isNotNull();
        assertThat(longs).hasSize(1);
        final Long aLong = longs.get(0);
        assertThat(aLong).isStrictlyBetween(0L, 1000L);
    }

    @Test
    void testGenerateListWhenCreateThenAddAnotherNumber() {
        final var app = new SpringFlash6Launcher();
        final var session = mock(HttpSession.class);
        final var numberList = new ArrayList<Integer>();
        when(session.getAttribute("numberList")).thenReturn(numberList);

        final List<Integer> integers = app.generateList(session);

        assertThat(integers).hasSize(1);
        assertThat(integers.get(0)).isStrictlyBetween(0, 1000);

        final List<Integer> integers2 = app.generateList(session);

        assertThat(integers2).hasSize(2);
        assertThat(integers2.get(0)).isEqualTo(integers.get(0));
        assertThat(integers2.get(1)).isStrictlyBetween(0, 1000);
    }
}