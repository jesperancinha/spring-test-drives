package org.jesperancinha.sftd.topic.container.converters;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CitrusEditorTest {
    @Test
    void testSetAsTextWhenSetTextThenTextIsOk() {
        final CitrusEditor citrusEditor = new CitrusEditor();
        citrusEditor.setAsText("lemon-123");
        final Object value = citrusEditor.getValue();

        assertThat(value).isInstanceOf(Lemon.class);
        assertThat(((Lemon) value).getSize()).isEqualTo(123);
    }
}