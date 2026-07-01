package org.jesperancinha.sftd.flash15.configuration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Flash15TraditionalConfiguration.class)
class Flash15TraditionalConfigurationTest {

    @Autowired
    private Flash15TraditionalConfiguration flash15TraditionalConfiguration;

    @Test
    public void testConfigurationWhenTraditionalThenCorrectConfiguration() {
        assertThat(flash15TraditionalConfiguration.getLyric1()).isEqualTo("And you say, \"As long as I'm here");
        assertThat(flash15TraditionalConfiguration.getLyric2()).isEqualTo("No one can hurt you");
    }

}