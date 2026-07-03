package org.jesperancinha.spring.flash412.beans.eating;

import org.springframework.stereotype.Component;

@Component
public class Beans implements Food {
    @Override
    public String showFood() {
        return "You can make a nice stew with these! These are beans";
    }
}
