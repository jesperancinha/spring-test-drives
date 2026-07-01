package org.jesperancinha.sftd.mastery2.portuguese.music

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
internal class Mastery2PortugueseMusicLauncherKotlinTest @Autowired constructor(
    @Value("\${server.port}")
    private val localServerPort: Long,
    @Value("\${local.management.port}")
    private val portActuatorPort: Long,
) {

    @BeforeEach
    fun setUp() {
    }

    @AfterEach
    fun tearDown() {
    }

    @Test
    fun testRunWhenAskingPortsThenGetSeparatePorts() {
        ConsolerizerComposer.out(" ")
            .brightGreen(localServerPort)
            .toConsoleLn()
        ConsolerizerComposer.out(" ")
            .orange(portActuatorPort)
            .toConsoleLn()
    }
}