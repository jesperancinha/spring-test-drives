package org.jesperancinha.sftd.flash7.session.handlers;

import org.springframework.http.HttpStatus;

public class ErrorFlower {
    private String message;
    private HttpStatus httpStatus;

    public ErrorFlower() {

    }

    public ErrorFlower(final String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
