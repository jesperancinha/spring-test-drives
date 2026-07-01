package org.jesperancinha.sftd.flash35.aop.security.southern.oracle;

import org.jesperancinha.sftd.flash35.aop.security.user.UserMessengerDetails;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.lang.NonNull;

import java.lang.reflect.Method;

import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.GREEN;
import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.RED;

public class SouthernOracleAdvice implements MethodBeforeAdvice {
    private final SouthernOracle southernOracle;

    public SouthernOracleAdvice() {
        this.southernOracle = new SouthernOracle();
    }

    @Override
    public void before(
            @NonNull
                    Method method,
            @NonNull
                    Object[] objects, Object target) {
        UserMessengerDetails user = southernOracle.getLoggedOnUser();
        if (user == null) {
            RED.printGenericLn("No user logged in. This could be the Nothing");
            throw new SecurityException("A suspected login was tried. Please check for the presence of the Nothing");
        } else if ("Atreyu".equals(user.getUserName())) {
            GREEN.printGenericLn("%s can log in!", user);
        } else {
            RED.printGenericLn("%s cannot log in!", user);
            throw new SecurityException(String.format("No access allowed for user -> %s", user));
        }
    }
}