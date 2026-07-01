package org.jesperancinha.sftd.flash515.factories.events;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.SpringApplicationEvent;

public class YellowTulipSpringApplicationEvent extends SpringApplicationEvent {
    public YellowTulipSpringApplicationEvent(SpringApplication application, String[] args) {
        super(application, args);
        ConsolerizerComposer.outSpace()
                .yellow("The yellow tulip is the start of this app")
                .reset();
    }
}
