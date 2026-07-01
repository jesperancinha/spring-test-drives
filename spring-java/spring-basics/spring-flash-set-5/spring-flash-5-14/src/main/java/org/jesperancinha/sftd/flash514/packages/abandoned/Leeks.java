package org.jesperancinha.sftd.flash514.packages.abandoned;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class Leeks {
    public Leeks() {
        ConsolerizerComposer.outSpace()
                .bgRed("If you see Leeks, then it means that something went wrong!")
                .red("This is not in the custom scan")
                .reset();
    }
}
