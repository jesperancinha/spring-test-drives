package org.jesperancinha.spring.tutorial.persistence.rest.exceptions;

public class FlowerNotAvailableException2 extends RuntimeException {
    public FlowerNotAvailableException2(final String flower) {
        super(String.format("Flower %s is not available!", flower));
    }
}
