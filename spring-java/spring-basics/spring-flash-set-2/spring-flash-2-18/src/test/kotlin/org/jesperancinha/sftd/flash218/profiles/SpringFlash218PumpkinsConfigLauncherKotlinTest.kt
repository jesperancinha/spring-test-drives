package org.jesperancinha.sftd.flash218.profiles

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("pumpkin")
internal class SpringFlash218PumpkinsConfigLauncherKotlinTest {
    @Test
    fun `should load context correctly`() {
    }
}