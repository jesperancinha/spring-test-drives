package org.jesperancinha.sftd.flash9.socksjs.controllers

import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.time.Month.APRIL
import java.time.format.DateTimeFormatter

class LocalDateTimeFormatterKotlinTest {
    @Test
    fun `should parse date correctly`() {
        val dateTimeFormatter: DateTimeFormatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss[.SSSSSS][.SSSSS][.SSSS][.SSS][.SS][.S]")
        val test = "2021-04-21T18:37:29.212795"
        dateTimeFormatter.parse(test)
            .shouldNotBeNull()
            .let { LocalDateTime.from(it) }
            .shouldNotBeNull()
            .apply {
                year shouldBe 2021
                month shouldBe APRIL
                dayOfMonth shouldBe 21
                hour shouldBe 18
                minute shouldBe 37
                second shouldBe  29
            }

    }
}