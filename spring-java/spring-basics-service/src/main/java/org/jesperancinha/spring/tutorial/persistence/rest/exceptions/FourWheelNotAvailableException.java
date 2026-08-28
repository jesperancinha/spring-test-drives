package org.jesperancinha.spring.tutorial.persistence.rest.exceptions;

public class FourWheelNotAvailableException extends RuntimeException {
    public FourWheelNotAvailableException(String fourwheels) {
        super(String.format("Four wheels are not avaialble at the moment, especially %s", fourwheels));
    }
}
