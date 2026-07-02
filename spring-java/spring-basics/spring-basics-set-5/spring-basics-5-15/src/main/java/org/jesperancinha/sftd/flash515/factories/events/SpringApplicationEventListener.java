package org.jesperancinha.sftd.flash515.factories.events;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.springframework.boot.context.event.SpringApplicationEvent;
import org.springframework.context.ApplicationListener;

public class SpringApplicationEventListener implements ApplicationListener<SpringApplicationEvent> {
    @Override
    public void onApplicationEvent(SpringApplicationEvent event) {
        ConsolerizerComposer.outSpace()
                .green(event)
                .reset();
    }
}
