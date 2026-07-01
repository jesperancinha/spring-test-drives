package org.jesperancinha.sftd.flash210.factory.configurations

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [TheUniverseConfiguration::class])
internal class TheUniverseConfigurationKotlinTest @Autowired constructor(
    @Autowired
    private val material1: Material
) {

    @Test
    fun testMaterial1_whenInjectThenGetMaterial2() {
       material1.value shouldBe "The world is not enough!"
    }
}