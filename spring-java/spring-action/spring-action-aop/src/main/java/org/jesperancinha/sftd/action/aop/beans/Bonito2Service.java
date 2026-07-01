package org.jesperancinha.sftd.action.aop.beans;

import org.aspectj.lang.JoinPoint;
import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.springframework.stereotype.Service;

@Service
public class Bonito2Service {

    public void waitPrivatelyForFishCatch() {
    }

    public void waitForFishCatch(JoinPoint joinPoint) {
        ConsolerizerComposer.outSpace()
                .bgBlue()
                .unicorns(100)
                .reset()
                .blue("Waiting for fish...");
    }

    public void beforeAnyCatch(JoinPoint joinPoint) {

    }
}
