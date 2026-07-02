package org.jesperancinha.spring.flash515.factories.events;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.springframework.context.ApplicationListener;

public class YellowTulipSpringEventListener implements ApplicationListener<YellowTulipSpringApplicationEvent> {
    @Override
    public void onApplicationEvent(YellowTulipSpringApplicationEvent event) {
        ConsolerizerComposer.outSpace()
                .yellow(event)
                .reset();
    }
}
