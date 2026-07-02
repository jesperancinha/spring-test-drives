package org.jesperancinha.sftd.flash51.stereotypes;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.springframework.stereotype.Repository;

@Repository
public class BlurRepositoryStereotypes {
    public BlurRepositoryStereotypes() {
        ConsolerizerComposer.outSpace()
                .yellow("Than stereotypes")
                .blue("This is Blur")
                .orange("Our annotations are %s", this.getClass().getAnnotations())
                .reset();
    }
}
