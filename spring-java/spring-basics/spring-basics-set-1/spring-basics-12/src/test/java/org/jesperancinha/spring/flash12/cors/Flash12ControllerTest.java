package org.jesperancinha.spring.flash12.cors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = Flash12Controller.class)
class Flash12ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testSentenceWhenCalledThenShowSentence() throws Exception {
        mockMvc.perform(get("/cors"))
                .andExpect(status().isOk())
                .andExpect(content().string("When accessing from spring-basics-12, there should be no blocking to visualization"));
    }

    @Test
    void testSentenceWhenCalledWithDifferentOriginThenFail() throws Exception {
        mockMvc.perform(get("/cors")
                .header("Origin", "http://thissiteissomethingcopletelydifferentlocalhost.com"))
                .andExpect(status().isForbidden())
                .andExpect(content().string("Invalid CORS request"));
    }

    @Test
    void testSentenceAlwaysWhenCalledThenShowSentence() throws Exception {
        mockMvc.perform(get("/always"))
                .andExpect(status().isOk())
                .andExpect(content().string("This should be blocked from the opposite url."));
    }

    @Test
    void testSentenceAlwaysWhenCalledDifferentOriginThenShowSentence() throws Exception {
        mockMvc.perform(get("/always")
                .header("Origin", "http://thissiteissomethingcopletelydifferentlocalhost.com"))
                .andExpect(status().isOk())
                .andExpect(content().string("This should be blocked from the opposite url."));
    }

    @Test
    void testSentenceProtectedWhenCalledThenShowSentence() throws Exception {
        mockMvc.perform(get("/protected"))
                .andExpect(status().isOk())
                .andExpect(content().string("When accessing from spring-basics-12, there should be no blocking to visualization"));
    }

    @Test
    void testSentenceProtectedWhenCalledDiffentOrigintThenFail() throws Exception {
        mockMvc.perform(get("/protected")
                .header("Origin", "http://thissiteissomethingcopletelydifferentlocalhost.com"))
                .andExpect(status().isForbidden())
                .andExpect(content().string("Invalid CORS request"));
    }
}