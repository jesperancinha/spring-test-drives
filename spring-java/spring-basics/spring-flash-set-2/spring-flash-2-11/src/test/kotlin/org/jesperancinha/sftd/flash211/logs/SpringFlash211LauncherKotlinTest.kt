package org.jesperancinha.sftd.flash211.logs

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class SpringFlash211LauncherKotlinTest {
    @Test
    fun testContext() {
        ConsolerizerComposer.outSpace()
            .magenta(ConsolerizerComposer.title("Log4J2 example"))
            .magenta("In this example we check how Spring Framework can use Log4J2 when it finds a log4j2 file")
            .reset()
    }
}