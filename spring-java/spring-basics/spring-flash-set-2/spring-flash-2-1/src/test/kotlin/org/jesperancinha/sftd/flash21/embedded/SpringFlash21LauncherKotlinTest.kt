package org.jesperancinha.sftd.flash21.embedded

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.web.WebAppConfiguration

@WebAppConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
internal class SpringFlash21LauncherKotlinTest {
    @Test
    fun `should run application context`() {
    }
}