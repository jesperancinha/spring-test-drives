package org.jesperancinha.sftd.flash51.stereotypes;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.springframework.stereotype.Component;

@Component
public class BlurComponentLife {
    BlurComponentLife() {
        ConsolerizerComposer.outSpace()
                .yellow("There must be more to life")
                .blue("This is Blur")
                .orange("Our annotations are %s", this.getClass().getAnnotations())
                .reset();
    }
}
