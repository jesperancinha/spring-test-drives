package org.jesperancinha.spring.flash32.rowcallbackhandler;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ExtendWith(MockitoExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class SpringFlash32LauncherTemplateTest {

    //https://github.com/spring-projects/spring-framework/issues/33991
    @MockitoBean
    private JdbcTemplate jdbcTemplate;

        private final ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);

    @Test
    void testContext() {
        verify(jdbcTemplate, times(1)).execute("CREATE TABLE WHEN_MUSIC(\n" +
                "   ID INT NOT NULL AUTO_INCREMENT,\n" +
                "   ARTIST VARCHAR(255) NOT NULL,\n" +
                "   WHEN_MUSIC VARCHAR(255) NOT NULL,\n" +
                "   PRIMARY KEY (ID)\n" +
                ");");
        verify(jdbcTemplate, times(1)).update("INSERT INTO WHEN_MUSIC(ARTIST, WHEN_MUSIC) VALUES (?, ?)", "The Doors", "The Music's Over");
        verify(jdbcTemplate, times(1)).update("INSERT INTO WHEN_MUSIC(ARTIST, WHEN_MUSIC) VALUES (?, ?)", "Green Day", "I come around");
        verify(jdbcTemplate, times(1)).query(stringArgumentCaptor.capture(), any(RowCallbackHandler.class));
        assertThat(stringArgumentCaptor.getValue()).isEqualTo("SELECT * FROM WHEN_MUSIC");
    }
}