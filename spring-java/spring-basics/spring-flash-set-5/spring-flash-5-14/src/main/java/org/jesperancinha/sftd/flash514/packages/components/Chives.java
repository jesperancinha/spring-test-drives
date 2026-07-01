package org.jesperancinha.sftd.flash514.packages.components;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class Chives {

    public Chives() {
        ConsolerizerComposer.outSpace()
                .green("Chives are here!")
                .reset();
    }
}
