package org.jesperancinha.spring.tutorial.handlers;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MixErrorMessage {
    private String message;

    public MixErrorMessage() {

    }

    public MixErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
