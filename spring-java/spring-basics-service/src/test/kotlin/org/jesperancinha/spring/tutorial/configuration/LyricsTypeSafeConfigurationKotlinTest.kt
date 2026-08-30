package org.jesperancinha.spring.tutorial.configuration

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class LyricsTypeSafeConfigurationKotlinTest @Autowired constructor(
    private val lyricsTypeSafeConfiguration: LyricsTypeSafeConfiguration,
) {

    @Test
    fun `should get the typesafe configuration`() {
        lyricsTypeSafeConfiguration.lyric3 shouldBe "Don't wanna lie here"
        lyricsTypeSafeConfiguration.lyric4 shouldBe "But you can learn to"
    }
}