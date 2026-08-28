package org.jesperancinha.spring.tutorial.persistence.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PotteryNotAvailableException extends RuntimeException {
    public PotteryNotAvailableException(String pottery) {
        super(String.format("No pottery is available, including %s", pottery));
    }
}
