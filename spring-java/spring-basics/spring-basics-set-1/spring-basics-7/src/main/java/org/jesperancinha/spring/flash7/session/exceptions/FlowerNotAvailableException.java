package org.jesperancinha.spring.flash7.session.exceptions;

public class FlowerNotAvailableException extends RuntimeException {
    public FlowerNotAvailableException(final String flower) {
        super(String.format("Flower %s is not available!", flower));
    }
}
