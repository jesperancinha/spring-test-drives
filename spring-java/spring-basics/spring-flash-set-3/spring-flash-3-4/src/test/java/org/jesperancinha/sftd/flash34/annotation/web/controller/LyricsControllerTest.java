package org.jesperancinha.sftd.flash34.annotation.web.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = LyricsController.class)
public class LyricsControllerTest {

    @Autowired
    private LyricsController lyricsController;

    @Test
    public void testCarpenters() throws Exception {
        final var carpenters = lyricsController.carpenters(null);

        assertThat(carpenters).isEqualTo("LyricCollection(band=The Carpenters, lyrics=[Top of The World])");
    }
}