package org.jesperancinha.spring.tutorial.handlers;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorCar {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
