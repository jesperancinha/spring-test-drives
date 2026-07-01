package org.jesperancinha.sftd.action.aop.beans;

import org.aspectj.lang.JoinPoint;
import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.springframework.stereotype.Service;

@Service
public class Bonito4Service {

    public void waitPrivatelyForFishCatch(JoinPoint joinPoint) {
        ConsolerizerComposer.outSpace()
                .bgBlue()
                .unicorns(100)
                .green(joinPoint)
                .blue("waitPrivatelyForFishCatch for fish...")
                .reset();
    }

    public void beforeAnyCatch(JoinPoint joinPoint) {

    }
}
