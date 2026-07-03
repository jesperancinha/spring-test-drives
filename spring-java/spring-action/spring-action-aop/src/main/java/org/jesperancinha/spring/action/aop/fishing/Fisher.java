package org.jesperancinha.spring.action.aop.fishing;

import org.jesperancinha.spring.action.aop.model.Cod;
import org.jesperancinha.spring.action.aop.model.SeaFood;

public class Fisher implements Harvester {

    public SeaFood harvest() {
        return new Cod();
    }

    public void justFishAnything() {

    }
}
