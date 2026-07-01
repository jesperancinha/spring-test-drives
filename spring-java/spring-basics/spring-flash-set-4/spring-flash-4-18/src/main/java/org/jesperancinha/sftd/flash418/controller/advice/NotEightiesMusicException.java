package org.jesperancinha.sftd.flash418.controller.advice;

public class NotEightiesMusicException extends RuntimeException {
    public NotEightiesMusicException(final Song song) {
        super(String.format("The song is %s. This song is not part of the 80's music movement of the 1980's", song));
    }
}
