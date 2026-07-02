package org.jesperancinha.sftd.flash27.monitoring;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

@Component
public class Flash27HealthIndicator implements HealthIndicator {
    private static final String[] LYRICS = new String[]{"Oh, not to touch a hair on your head", "Leave you as you are", "If he felt he had to direct you", "Then direct you into my arms"};
    private static final Status[] STATUSES = new Status[]{Status.DOWN, Status.UNKNOWN, Status.UP, Status.OUT_OF_SERVICE};

    @Override
    public Health health() {
        int i = (int) (4 * Math.random());
        return Health.status(STATUSES[i]).withDetail("lyric", LYRICS[i]).build();
    }
}
