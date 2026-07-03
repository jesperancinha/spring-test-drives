package org.jesperancinha.spring.flash210.factory.configurations

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [TheWorldConfiguration::class])
internal class TheWorldConfigurationKotlinTest @Autowired constructor(
    private val material2: Material
) {
    @Test
    fun testMaterial2whenInjectionGetMaterial2() {
        material2.value shouldBe "If you are strong enough!"
    }
}