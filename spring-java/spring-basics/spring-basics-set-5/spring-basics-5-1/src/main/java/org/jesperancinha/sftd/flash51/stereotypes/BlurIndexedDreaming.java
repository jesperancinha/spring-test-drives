package org.jesperancinha.sftd.flash51.stereotypes;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.springframework.stereotype.Indexed;

@Indexed
public class BlurIndexedDreaming {
    BlurIndexedDreaming() {
        ConsolerizerComposer.outSpace()
                .yellow("And then you start dreaming")
                .blue("This is Blur")
                .orange("Our annotations are %s", this.getClass().getAnnotations())
                .reset();
    }
}
