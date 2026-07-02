package org.jesperancinha.sftd.flash515.factories.events;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public class ApplicationEventListener implements ApplicationListener<ApplicationEvent> {

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        ConsolerizerComposer.outSpace()
                .cyan(event)
                .reset();
    }
}
