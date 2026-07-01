package org.jesperancinha.sftd.mastery2.portuguese.music;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
class Mastery2PortugueseMusicLauncherTest {

    @Value("${server.port}")
    private Long localServerPort;

    @Value("${local.management.port}")
    private Long portActuatorPort;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testRunWhenAskingPortsThenGetSeparatePorts() {
        ConsolerizerComposer.out(" ")
                .brightGreen(localServerPort)
                .toConsoleLn();
        ConsolerizerComposer.out(" ")
                .orange(portActuatorPort)
                .toConsoleLn();
    }
}