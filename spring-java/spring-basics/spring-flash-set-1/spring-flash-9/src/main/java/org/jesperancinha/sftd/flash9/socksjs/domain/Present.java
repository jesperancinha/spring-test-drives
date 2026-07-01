package org.jesperancinha.sftd.flash9.socksjs.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.Objects;

public class Present {
    private final String message;
    private final String response;
    private final LocalDateTime localDateTime;
    private final LocalDateTime systemDateTime;

    private final String[] HELLOS = new String[]{"Hi there!", "Oh Hello!", "Hi!", "Hi! How are you?"};

    @JsonCreator
    public Present(
            @JsonProperty("request")
                    Request request) {
        if (Objects.nonNull(request)) {
            this.message = request.getMessage();
            this.localDateTime = request.getLocalDateTime();
            this.systemDateTime = LocalDateTime.now();
            this.response = calculateResponse("" + request.getMessage());
        } else {
            this.message = null;
            this.localDateTime = null;
            this.systemDateTime = null;
            this.response = null;
        }
    }

    private String calculateResponse(String message) {
        if (message.toLowerCase().contains("snap")) {
            return "Rhythm is a dancer!";
        }
        if (message.toLowerCase().contains("soup")) {
            return "Baby instant soup doesn't really grab me, today I need something more sub-sub-sub-substantial";
        }
        if (message.toLowerCase().contains("hello")) {
            return HELLOS[(int) (Math.random() * HELLOS.length)];
        }
        return "I don't understand you. Can you explain a bit more? Check the Readme.md file for more details 😊";
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public LocalDateTime getSystemDateTime() {
        return systemDateTime;
    }

    public String getResponse() {
        return response;
    }
}
