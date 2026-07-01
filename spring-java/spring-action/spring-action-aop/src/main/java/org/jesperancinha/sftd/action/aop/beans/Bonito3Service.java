package org.jesperancinha.sftd.action.aop.beans;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.springframework.stereotype.Service;

@Service
public class Bonito3Service {

    public void waitPrivatelyForFishCatchWithClaw() {
    }

    public void waitPrivatelyForFishCatchWithHand() {
    }

    public void waitForFishCatch() {
        ConsolerizerComposer.outSpace()
                .bgYellow()
                .unicorns(100)
                .reset()
                .red("We send a red light when we catch by hand or by claw")
                .reset();
    }

    public void waitForFishNoClawCatch() {
        ConsolerizerComposer.outSpace()
                .bgYellow()
                .unicorns(100)
                .reset()
                .cyan("We send a cyan light when we don't catch by claw")
                .reset();
    }

}
