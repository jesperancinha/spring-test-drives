package org.jesperancinha.sftd.action.aop.fishing;

import org.jesperancinha.sftd.action.aop.model.Cod;
import org.jesperancinha.sftd.action.aop.model.SeaFood;

public class Fisher implements Harvester {

    public SeaFood harvest() {
        return new Cod();
    }

    public void justFishAnything() {

    }
}
