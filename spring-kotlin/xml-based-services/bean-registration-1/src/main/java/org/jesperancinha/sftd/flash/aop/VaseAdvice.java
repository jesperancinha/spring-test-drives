package org.jesperancinha.sftd.flash.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.BRIGHT_BLUE;
import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.RED;

public class VaseAdvice implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        BRIGHT_BLUE.printGenericLn("Seeds are planted!");
        final Object object = methodInvocation.proceed();
        RED.printGenericLn(object);
        RED.printGenericLn(object.hashCode());
        return object;
    }
}
