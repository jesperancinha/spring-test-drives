package org.jesperancinha.sftd.action.aop.beans;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.springframework.stereotype.Service;

@Service
public class Bonito1Service {

    public void waitForFishCatch() {
        ConsolerizerComposer.outSpace()
                .bgGreen()
                .unicorns(100)
                .reset()
                .blue("Waiting for fish...");

    }

    public void waitForFishNoCatch() {
        ConsolerizerComposer.outSpace()
                .bgGreen()
                .unicorns(100)
                .reset()
                .blue("Waiting for fish...");

    }
}
