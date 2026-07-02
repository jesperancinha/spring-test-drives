package org.jesperancinha.sftd.flash9.socksjs.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class Request {
    private String message;

    private LocalDateTime localDateTime;

    public String getMessage() {
        return message;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Request request)) return false;
        return Objects.equals(getMessage(), request.getMessage()) && Objects.equals(getLocalDateTime(), request.getLocalDateTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMessage(), getLocalDateTime());
    }
}
