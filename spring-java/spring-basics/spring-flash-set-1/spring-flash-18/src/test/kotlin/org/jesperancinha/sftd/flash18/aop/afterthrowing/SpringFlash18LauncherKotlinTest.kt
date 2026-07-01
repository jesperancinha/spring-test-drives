package org.jesperancinha.sftd.flash18.aop.afterthrowing

import org.jesperancinha.sftd.flash18.aop.afterthrowing.SpringFlash18Launcher.mutateLyricsServiceImpl
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.context.ApplicationContext

@SpringBootTest(webEnvironment = RANDOM_PORT)
internal class SpringFlash18LauncherKotlinTest @Autowired constructor(
    val applicationContext: ApplicationContext
) {
    @Test
    fun `should start application and initialize application context`() {
        mutateLyricsServiceImpl(applicationContext)
    }
}