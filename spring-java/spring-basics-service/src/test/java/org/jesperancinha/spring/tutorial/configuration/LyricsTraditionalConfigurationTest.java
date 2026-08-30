package org.jesperancinha.spring.tutorial.configuration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = LyricsTraditionalConfiguration.class)
class LyricsTraditionalConfigurationTest {

    @Autowired
    private LyricsTraditionalConfiguration lyricsTraditionalConfiguration;

    @Test
    public void testConfigurationWhenTraditionalThenCorrectConfiguration() {
        assertThat(lyricsTraditionalConfiguration.getLyric1()).isEqualTo("And you say, \"As long as I'm here");
        assertThat(lyricsTraditionalConfiguration.getLyric2()).isEqualTo("No one can hurt you");
    }

}