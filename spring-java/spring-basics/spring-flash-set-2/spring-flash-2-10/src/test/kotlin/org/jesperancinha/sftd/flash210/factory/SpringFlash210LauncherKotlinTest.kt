package org.jesperancinha.sftd.flash210.factory

import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer
import org.jesperancinha.sftd.flash210.factory.configurations.Material
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@SpringBootTest(webEnvironment = RANDOM_PORT)
internal class SpringFlash210LauncherKotlinTest(
    @Autowired
    private val material: Material
) {
    @Test
    fun testContext() {
        material
            .shouldNotBeNull()
            .value shouldBe "The world is not enough!"
        ConsolerizerComposer.outSpace()
            .brightBlue()
            .jsonPrettyPrint(material)
            .reset()
    }
}