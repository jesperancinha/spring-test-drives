package org.jesperancinha.sftd.flash51.stereotypes;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.springframework.stereotype.Service;

@Service
public class BlurServiceBnB {
    BlurServiceBnB() {
        ConsolerizerComposer.outSpace()
                .yellow("Runs a little BnB")
                .blue("This is Blur")
                .orange("Our annotations are %s", this.getClass().getAnnotations())
                .reset();
    }
}
