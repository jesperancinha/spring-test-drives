package org.jesperancinha.sftd.flash15.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:traditional.properties")
public class Flash15TraditionalConfiguration {

    @Value("${jeorg.flash15.lyrics.lyric1}")
    private String lyric1;

    @Value("${jeorg.flash15.lyrics.lyric2}")
    private String lyric2;

    public String getLyric2() {
        return lyric2;
    }

    public String getLyric1() {
        return lyric1;
    }

    public void setLyric2(String lyric2) {
        this.lyric2 = lyric2;
    }

    public void setLyric1(String lyric1) {
        this.lyric1 = lyric1;
    }
}
