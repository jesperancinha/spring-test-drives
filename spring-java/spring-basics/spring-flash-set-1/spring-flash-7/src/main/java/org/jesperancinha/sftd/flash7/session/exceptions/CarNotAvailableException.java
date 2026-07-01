package org.jesperancinha.sftd.flash7.session.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CarNotAvailableException extends RuntimeException {
    public CarNotAvailableException(final String car) {
        super(String.format("Car %s is not available", car));
    }
}
