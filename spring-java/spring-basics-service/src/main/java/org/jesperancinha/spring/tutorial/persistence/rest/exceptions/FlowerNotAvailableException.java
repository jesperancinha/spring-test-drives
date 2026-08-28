package org.jesperancinha.spring.tutorial.persistence.rest.exceptions;

public class FlowerNotAvailableException extends RuntimeException {
    public FlowerNotAvailableException(final String flower) {
        super(String.format("Flower %s is not available!", flower));
    }
}
