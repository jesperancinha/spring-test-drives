package org.jesperancinha.sftd.flash515.factories.events;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.springframework.context.ApplicationEvent;

public class RedTulipEvent extends ApplicationEvent {
    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public RedTulipEvent(Object source) {
        super(source);
        ConsolerizerComposer.outSpace()
                .red("Red Tulip underway!")
                .reset();
    }
}
