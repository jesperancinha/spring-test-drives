package org.jesperancinha.sftd.flash218.profiles

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("garbage")
internal class SpringFlash218GarbageConfigLauncherKotlinTest {
    @Test
    fun `should load context correctly`() {
    }
}