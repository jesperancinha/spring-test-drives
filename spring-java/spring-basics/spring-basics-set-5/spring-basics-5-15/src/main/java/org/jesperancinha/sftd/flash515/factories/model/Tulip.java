package org.jesperancinha.sftd.flash515.factories.model;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.springframework.stereotype.Component;

@Component
public class Tulip {

    public Tulip() {

    }

    public void grow() {
        ConsolerizerComposer.outSpace()
                .red("I'm a great Tulip!")
                .reset();
    }
}
