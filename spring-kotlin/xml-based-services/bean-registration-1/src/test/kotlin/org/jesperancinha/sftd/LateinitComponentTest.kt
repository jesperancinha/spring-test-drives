package org.jesperancinha.sftd

import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.types.shouldBeTypeOf
import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.stereotype.Component

@Component
class MyComponent {

    init {
        ConsolerizerComposer.outSpace().bgMagenta("Service started!")
    }
// This will fail injection because bean test does not exist
// @Autowired
    lateinit var test: String
}

@SpringBootApplication
class StarterApplication {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(StarterApplication::class.java, *args)
        }
    }
}

@SpringBootTest
class LateinitComponentTest @Autowired constructor(
    val mycomponent: MyComponent,
) {

    @Test
    fun `should run and not initialize test`() {
        val onFailure = runCatching {
            mycomponent.test
        }.onFailure {
            ConsolerizerComposer.outSpace().bgRed(it)
            it.shouldBeTypeOf<UninitializedPropertyAccessException>()
        }
        onFailure.isFailure.shouldBeTrue()
    }
}