package org.jesperancinha.sftd.flash210.factory.configurations;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TheUniverseConfiguration.class)
class TheUniverseConfigurationTest {

    @Autowired
    private Material material1;

    @Test
    void testMaterial1_whenInjectThenGetMaterial2() {
        assertThat(material1.getValue()).isEqualTo("The world is not enough!");
    }
}