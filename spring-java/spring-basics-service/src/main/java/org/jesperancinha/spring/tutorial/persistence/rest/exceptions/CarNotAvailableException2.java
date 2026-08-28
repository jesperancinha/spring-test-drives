package org.jesperancinha.spring.tutorial.persistence.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CarNotAvailableException2 extends RuntimeException {
    public CarNotAvailableException2(final String car) {
        super(String.format("Car %s is not available", car));
    }
}
