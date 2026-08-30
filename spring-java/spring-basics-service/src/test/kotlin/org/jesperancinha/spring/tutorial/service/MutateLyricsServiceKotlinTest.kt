package org.jesperancinha.spring.tutorial.service

import org.jesperancinha.spring.tutorial.SpringBasicsTutorialPart1.mutateLyricsServiceImpl
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.context.ApplicationContext

@SpringBootTest(webEnvironment = RANDOM_PORT)
internal class MutateLyricsServiceKotlinTest @Autowired constructor(
    private val applicationContext: ApplicationContext
) {
    @Test
    fun `should start application and initialize application context`() {
        mutateLyricsServiceImpl(applicationContext)
    }
}