package org.jesperancinha.sftd.topic.container.converters

import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf
import org.junit.jupiter.api.Test

internal class CitrusEditorKotlinTest {
    @Test
    fun `should get correct Lemon value`() {
        val citrusEditor = CitrusEditor()
        citrusEditor.asText = "lemon-123"
        val value = citrusEditor.value
        value.shouldBeTypeOf<Lemon>()
        value.size shouldBe 123
    }
    @Test
    fun `should get correct Lime value`() {
        val citrusEditor = CitrusEditor()
        citrusEditor.asText = "lime-124"
        val value = citrusEditor.value
        value.shouldBeTypeOf<Lime>()
        value.size shouldBe 124
    }
}