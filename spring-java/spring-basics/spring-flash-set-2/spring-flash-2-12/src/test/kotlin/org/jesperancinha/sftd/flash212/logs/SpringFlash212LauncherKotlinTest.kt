package org.jesperancinha.sftd.flash212.logs

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class SpringFlash212LauncherKotlinTest {
    @Test
    fun testContext() {
        ConsolerizerComposer.outSpace()
            .magenta(ConsolerizerComposer.title("Logback example"))
            .magenta("In this example we check how Spring Framework can use Logback when it finds a logback file")
            .reset()
    }
}