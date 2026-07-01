package org.jesperancinha.sftd.flash7.session.exceptions2;

public class FlowerNotAvailableException2 extends RuntimeException {
    public FlowerNotAvailableException2(final String flower) {
        super(String.format("Flower %s is not available!", flower));
    }
}
