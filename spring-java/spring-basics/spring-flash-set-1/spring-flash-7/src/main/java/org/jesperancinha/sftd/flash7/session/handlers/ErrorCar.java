package org.jesperancinha.sftd.flash7.session.handlers;

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
