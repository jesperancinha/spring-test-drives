package org.jesperancinha.sftd.action.aop.beans;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.springframework.stereotype.Service;

@Service
public class CodService {

    public void withinTheCode() {
        ConsolerizerComposer.outSpace()
                .none()
                .white("We are going to catch a")
                .magenta("CodFish")
                .white("!")
                .magenta("You see? We always say that")
                .red("within")
                .magenta("...")
                .newLine()
                .reset();
    }
}
