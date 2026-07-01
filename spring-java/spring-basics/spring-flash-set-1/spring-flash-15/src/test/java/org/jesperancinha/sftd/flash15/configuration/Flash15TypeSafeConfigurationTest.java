package org.jesperancinha.sftd.flash15.configuration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class Flash15TypeSafeConfigurationTest {

    @Autowired
    private Flash15TypeSafeConfiguration flash15TypeSafeConfiguration;

    @Test
    public void testConfigurationWhenTypeSafeThenCorrectConfiguration() {
        assertThat(flash15TypeSafeConfiguration.getLyric3()).isEqualTo("Don't wanna lie here");
        assertThat(flash15TypeSafeConfiguration.getLyric4()).isEqualTo("But you can learn to");
    }

}