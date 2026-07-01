package org.jesperancinha.sftd.action.aop.pickers;

import org.jesperancinha.sftd.action.aop.annotations.HighQuality;
import org.jesperancinha.sftd.action.aop.model.Oyster;

public class OysterPicker implements Picker<Oyster>{
    @Override
    public Oyster pickup(Oyster oyster) {
        return oyster;
    }

    @Override
    public Oyster pickWithQuality(@HighQuality Oyster oyster) {
        return oyster;
    }


}
