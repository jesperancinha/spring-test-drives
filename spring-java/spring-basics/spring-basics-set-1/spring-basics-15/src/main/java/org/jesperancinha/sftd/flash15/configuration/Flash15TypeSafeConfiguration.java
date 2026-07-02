package org.jesperancinha.sftd.flash15.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:typesafe.properties")
@ConfigurationProperties("jeorg.flash15.lyrics")
public class Flash15TypeSafeConfiguration {

    private String lyric3;
    private String lyric4;

    public String getLyric3() {
        return lyric3;
    }

    public String getLyric4() {
        return lyric4;
    }

    public void setLyric3(String lyric3) {
        this.lyric3 = lyric3;
    }

    public void setLyric4(String lyric4) {
        this.lyric4 = lyric4;
    }
}
