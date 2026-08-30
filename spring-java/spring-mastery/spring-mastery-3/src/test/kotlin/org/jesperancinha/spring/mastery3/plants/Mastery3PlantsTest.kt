package org.jesperancinha.spring.mastery3.plants

import org.jesperancinha.console.consolerizer.common.ConsolerizerColor
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Timeout
import org.junit.jupiter.api.parallel.Execution
import org.junit.jupiter.api.parallel.ExecutionMode.SAME_THREAD
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@SpringBootTest(webEnvironment = RANDOM_PORT)
@Execution(SAME_THREAD)
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