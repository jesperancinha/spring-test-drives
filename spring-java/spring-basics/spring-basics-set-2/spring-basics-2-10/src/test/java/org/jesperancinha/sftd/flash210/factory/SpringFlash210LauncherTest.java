package org.jesperancinha.sftd.flash210.factory;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.jesperancinha.sftd.flash210.factory.configurations.Material;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class SpringFlash210LauncherTest {

    @Autowired
    private Material material;

    @Test
    void testContext() {
        assertThat(material).isNotNull();
        final var value = material.getValue();
        assertThat(value).isEqualTo("The world is not enough!");
        ConsolerizerComposer.outSpace()
                .brightBlue()
                .jsonPrettyPrint(material)
                .reset();
        SpringFlash210Launcher.main(new String[0]);
    }
}