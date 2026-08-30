package org.jesperancinha.spring.tutorial.configuration

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [LyricsTraditionalConfiguration::class])
internal class LyricsTraditionalConfigurationKotlinTest @Autowired constructor(
    private val lyricsTraditionalConfiguration: LyricsTraditionalConfiguration,
) {

    @Test
    fun `should get the tradition configuration`() {
        lyricsTraditionalConfiguration.lyric1 shouldBe "And you say, \"As long as I'm here"
        lyricsTraditionalConfiguration.lyric2 shouldBe "No one can hurt you"
    }
}