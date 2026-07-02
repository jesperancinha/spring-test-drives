package org.jesperancinha.sftd.flash9.socksjs.controllers

import io.kotest.matchers.collections.shouldBeIn
import io.kotest.matchers.date.shouldBeWithin
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import org.jesperancinha.sftd.flash9.socksjs.domain.Present
import org.jesperancinha.sftd.flash9.socksjs.domain.Request
import org.junit.jupiter.api.Test
import java.lang.reflect.Field
import java.time.Duration.ofMillis
import java.time.LocalDateTime

internal class PresentControllerKotlinTest {
    private val presentController: PresentController = PresentController()

    @Test
    fun `when present is sent and ask for snap it should reply with Rythm is a dancer`() {
        val request = Request().apply {
            message = "snap"
            localDateTime = LocalDateTime.now()
        }

        presentController.sendPresent(request)
            .shouldNotBeNull()
            .apply {
                localDateTime shouldBe request.localDateTime
                systemDateTime.shouldBeWithin(ofMillis(10),request.localDateTime)
                response shouldBe "Rhythm is a dancer!"
                message shouldBe "snap"
            }
    }

    @Test
    fun `when present is send and ask for soup it should get Sidewinder`() {
        val request = Request()
        request.message = "soup"
        request.localDateTime = LocalDateTime.now()
        presentController.sendPresent(request)
            .shouldNotBeNull()
            .apply {
                localDateTime shouldBe request.localDateTime
                systemDateTime.shouldBeWithin(ofMillis(10),request.localDateTime)
                response shouldBe "Baby instant soup doesn't really grab me, today I need something more sub-sub-sub-substantial"
                message shouldBe "soup"
            }
    }

    @Test
    @Throws(NoSuchFieldException::class, IllegalAccessException::class)
    fun `when send a present with hello it should get Hello array`() {
        val request = Request()
        request.message = "hello"
        request.localDateTime = LocalDateTime.now()
        val present = presentController.sendPresent(request)
        val hellos: Field = Present::class.java.getDeclaredField("HELLOS")
        hellos.isAccessible = true
        val helloArray = hellos.get(present) as Array<*>
        present.shouldNotBeNull()
            .apply {
                localDateTime shouldBe request.localDateTime
                systemDateTime.shouldBeWithin(ofMillis(10),request.localDateTime)
                response.shouldBeIn(*helloArray)
                message shouldBe "hello"
            }
    }

    @Test
    fun `when sending a present to unknown then get unknown`() {
        val request = Request()
        request.message = "Something good"
        request.localDateTime = LocalDateTime.now()
        presentController.sendPresent(request)
            .shouldNotBeNull()
            .apply {
                localDateTime shouldBe request.localDateTime
                systemDateTime.shouldBeWithin(ofMillis(10),request.localDateTime)
                response shouldBe "I don't understand you. Can you explain a bit more? Check the Readme.md file for more details 😊"
                message shouldBe "Something good"
            }
    }
}