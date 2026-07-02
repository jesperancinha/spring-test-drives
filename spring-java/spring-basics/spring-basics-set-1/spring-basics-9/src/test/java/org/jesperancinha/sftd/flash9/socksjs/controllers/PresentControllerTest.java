package org.jesperancinha.sftd.flash9.socksjs.controllers;

import org.jesperancinha.sftd.flash9.socksjs.domain.Present;
import org.jesperancinha.sftd.flash9.socksjs.domain.Request;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class PresentControllerTest {

    private final PresentController presentController = new PresentController();

    @Test
    void testSendPresentWhenAskingForSnapThenGetRhythmIsADancer() {
        final var request = new Request();
        request.setMessage("snap");
        request.setLocalDateTime(LocalDateTime.now());

        final var present = presentController.sendPresent(request);

        assertThat(present).isNotNull();
        assertThat(present.getLocalDateTime()).isEqualTo(request.getLocalDateTime());
        assertThat(present.getSystemDateTime()).isNotEqualTo(request.getLocalDateTime());
        assertThat(present.getResponse()).isEqualTo("Rhythm is a dancer!");
        assertThat(present.getMessage()).isEqualTo("snap");
    }

    @Test
    void testSendPresentWhenAskingForSoupThenGetSidewinder() {
        final var request = new Request();
        request.setMessage("soup");
        request.setLocalDateTime(LocalDateTime.now());

        final var present = presentController.sendPresent(request);

        assertThat(present).isNotNull();
        assertThat(present.getLocalDateTime()).isEqualTo(request.getLocalDateTime());
        assertThat(present.getSystemDateTime()).isNotEqualTo(request.getLocalDateTime());
        assertThat(present.getResponse()).isEqualTo("Baby instant soup doesn't really grab me, today I need something more sub-sub-sub-substantial");
        assertThat(present.getMessage()).isEqualTo("soup");
    }

    @Test
    void testSendPresentWhenAskingHelloThenGetHello() throws NoSuchFieldException, IllegalAccessException {
        final var request = new Request();
        request.setMessage("hello");
        request.setLocalDateTime(LocalDateTime.now());

        final var present = presentController.sendPresent(request);

        final Field hellos = Present.class.getDeclaredField("HELLOS");
        hellos.setAccessible(true);
        final var helloArray = (String[]) hellos.get(present);
        assertThat(present).isNotNull();
        assertThat(present.getLocalDateTime()).isEqualTo(request.getLocalDateTime());
        assertThat(present.getSystemDateTime()).isNotEqualTo(request.getLocalDateTime());
        assertThat(present.getResponse()).isIn((Object[]) helloArray);
        assertThat(present.getMessage()).isEqualTo("hello");
    }

    @Test
    void testSendPresentWhenUnknownThenGetUnknown() {
        final var request = new Request();
        request.setMessage("Something good");
        request.setLocalDateTime(LocalDateTime.now());

        final var present = presentController.sendPresent(request);

        assertThat(present).isNotNull();
        assertThat(present.getLocalDateTime()).isEqualTo(request.getLocalDateTime());
        assertThat(present.getSystemDateTime()).isNotEqualTo(request.getLocalDateTime());
        assertThat(present.getResponse()).isEqualTo("I don't understand you. Can you explain a bit more? Check the Readme.md file for more details 😊");
        assertThat(present.getMessage()).isEqualTo("Something good");
    }
}