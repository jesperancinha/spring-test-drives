package org.jesperancinha.sftd.flash412.beans.eating;

import org.springframework.stereotype.Component;

@Component
public class Pork implements Food {
    @Override
    public String showFood() {
        return "You can add bits of this to enrich the flavor of some foods! This is pork and this a good healthy quality one";
    }
}
