package org.jesperancinha.sftd.topic.container

import com.fasterxml.jackson.core.JsonProcessingException
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import org.jesperancinha.sftd.topic.container.beans.BeanWithContructor
import org.junit.jupiter.api.Test

internal class ContainerLauncherKotlinTest {
    @Test
    @Throws(JsonProcessingException::class)
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