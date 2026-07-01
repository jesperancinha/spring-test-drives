package org.jesperancinha.sftd.flash15.configuration

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [Flash15TraditionalConfiguration::class])
internal class Flash15TraditionalConfigurationKotlinTest @Autowired constructor(
    @Autowired
    private val flash15TraditionalConfiguration: Flash15TraditionalConfiguration,
) {

    @Test
    fun `should get the tradition configuration`() {
        flash15TraditionalConfiguration.lyric1 shouldBe "And you say, \"As long as I'm here"
        flash15TraditionalConfiguration.lyric2 shouldBe "No one can hurt you"
    }
}