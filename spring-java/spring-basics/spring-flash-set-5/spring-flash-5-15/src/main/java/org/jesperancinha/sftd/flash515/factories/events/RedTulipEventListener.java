package org.jesperancinha.sftd.flash515.factories.events;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.springframework.context.ApplicationListener;

public class RedTulipEventListener implements ApplicationListener<RedTulipEvent> {

    @Override
    public void onApplicationEvent(RedTulipEvent event) {
        ConsolerizerComposer.outSpace()
                .red(event)
                .reset();
    }
}
