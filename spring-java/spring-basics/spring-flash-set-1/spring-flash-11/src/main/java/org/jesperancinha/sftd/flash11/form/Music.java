package org.jesperancinha.sftd.flash11.form;

import java.util.Objects;

public class Music {

    private String artist;
    private String song;

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    @Override
    public String toString() {
        return "Music{" +
                "artist='" + artist + '\'' +
                ", song='" + song + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Music)) return false;
        Music music = (Music) o;
        return getArtist().equals(music.getArtist()) && getSong().equals(music.getSong());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getArtist(), getSong());
    }
}
