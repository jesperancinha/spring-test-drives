package org.jesperancinha.sftd.flash516.shutdown.controller;

import jakarta.annotation.PreDestroy;
import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.springframework.stereotype.Component;

@Component
public class SkynetNetwork {
    @PreDestroy
    public void preDestroy() {
        ConsolerizerComposer
                .outSpace()
                .cyan("Yeeehhaaaaa!!!")
                .reset();
    }
}
