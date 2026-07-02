package org.jesperancinha.sftd.flash36.cglib.enhancer;

public class Tomato {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String pickupTomato() {
        return "I'm out of the box! Well... it's an illusion...";
    }

    public String makeKetchup() {
        return "No ketchup yet!";
    }

    @Override
    public String toString() {
        return "Tomato{" +
                "message='" + message + '\'' +
                '}';
    }
}
