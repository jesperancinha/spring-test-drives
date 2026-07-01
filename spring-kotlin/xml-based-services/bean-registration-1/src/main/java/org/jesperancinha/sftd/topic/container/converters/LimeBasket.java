package org.jesperancinha.sftd.topic.container.converters;

import java.beans.ConstructorProperties;

public class LimeBasket {

    private Citrus citrus1;

    private Citrus citrus2;

    @ConstructorProperties({"citrus1", "citrus2"})
    public LimeBasket(Citrus citrus1, Citrus citrus2) {
        this.citrus1 = citrus1;
        this.citrus2 = citrus2;
    }

    public Citrus getCitrus1() {
        return citrus1;
    }

    public Citrus getCitrus2() {
        return citrus2;
    }

    public void setCitrus1(Citrus citrus1) {
        this.citrus1 = citrus1;
    }

    public void setCitrus2(Citrus citrus2) {
        this.citrus2 = citrus2;
    }

    @Override
    public String toString() {
        return "LimeBasket{" +
                "citrus1=" + citrus1 +
                ", citrus2=" + citrus2 +
                '}';
    }
}
