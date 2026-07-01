package org.jesperancinha.sftd.flash317.profile;

public class Messenger {
    private String message;

    public Messenger() {
    }

    public Messenger(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Messenger{" +
                "message='" + message + '\'' +
                '}';
    }
}
