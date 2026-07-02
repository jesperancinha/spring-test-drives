package org.jesperancinha.sftd.flash59.aop.service;

import org.jesperancinha.sftd.flash59.aop.annotation.Diet;

public interface Recipe {

    String bake();

    @Diet
    String bakeDiet();
}
