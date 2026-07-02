package org.jesperancinha.sftd.flash7.session.exceptions2;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CarNotAvailableException2 extends RuntimeException {
    public CarNotAvailableException2(final String car) {
        super(String.format("Car %s is not available", car));
    }
}
