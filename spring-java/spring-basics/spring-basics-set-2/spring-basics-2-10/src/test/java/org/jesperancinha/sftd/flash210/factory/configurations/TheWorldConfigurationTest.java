package org.jesperancinha.sftd.flash210.factory.configurations;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TheWorldConfiguration.class)
class TheWorldConfigurationTest {

    @Autowired
    private Material material2;

    @Test
    void testMaterial2whenInjectionGetMaterial2() {
        assertThat(material2.getValue()).isEqualTo("If you are strong enough!");
    }
}