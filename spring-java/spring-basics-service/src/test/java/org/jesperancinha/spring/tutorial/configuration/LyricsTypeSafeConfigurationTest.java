package org.jesperancinha.spring.tutorial.configuration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class LyricsTypeSafeConfigurationTest {

    @Autowired
    private LyricsTypeSafeConfiguration lyricsTypeSafeConfiguration;

    @Test
    public void testConfigurationWhenTypeSafeThenCorrectConfiguration() {
        assertThat(lyricsTypeSafeConfiguration.getLyric3()).isEqualTo("Don't wanna lie here");
        assertThat(lyricsTypeSafeConfiguration.getLyric4()).isEqualTo("But you can learn to");
    }

}