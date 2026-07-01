package org.jesperancinha.sftd.mastery3.plants

import org.jesperancinha.console.consolerizer.common.ConsolerizerColor
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Timeout
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class Mastery3PlantsTest {
    @Test
    @Timeout(1)
    @Disabled
    fun `should load context with timeout`() {
        try {
            Thread.sleep(2000)
        } catch (e: InterruptedException) {
            ConsolerizerColor.RED.printThrowableAndExit(e)
        }
    }

    @Test
    fun `should load context`() {
    }
}