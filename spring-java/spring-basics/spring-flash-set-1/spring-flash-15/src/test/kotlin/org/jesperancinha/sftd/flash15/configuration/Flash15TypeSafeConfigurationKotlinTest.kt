package org.jesperancinha.sftd.flash15.configuration

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class Flash15TypeSafeConfigurationKotlinTest @Autowired constructor(
    @Autowired
    private val flash15TypeSafeConfiguration: Flash15TypeSafeConfiguration,
) {

    @Test
    fun `should get the typesafe configuration`() {
        flash15TypeSafeConfiguration.lyric3 shouldBe "Don't wanna lie here"
        flash15TypeSafeConfiguration.lyric4 shouldBe "But you can learn to"
    }
}