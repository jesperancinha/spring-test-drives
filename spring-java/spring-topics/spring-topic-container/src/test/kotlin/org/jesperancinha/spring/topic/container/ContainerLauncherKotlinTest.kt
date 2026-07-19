package org.jesperancinha.spring.topic.container

import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import org.jesperancinha.spring.topic.container.beans.BeanWithContructor
import org.junit.jupiter.api.Test

internal class ContainerLauncherKotlinTest {
    @Test
    fun testContext() {
        ContainerLauncher.main(arrayOf())
    }

    @Test
    fun `should call properties`() {
        BeanWithContructor("sci", "shell", "red")
            .should { beanWithConstructor ->
                beanWithConstructor.name shouldBe "shell"
                beanWithConstructor.scientificName shouldBe "sci"
                beanWithConstructor.mainColor shouldBe "red"
            }

    }
}