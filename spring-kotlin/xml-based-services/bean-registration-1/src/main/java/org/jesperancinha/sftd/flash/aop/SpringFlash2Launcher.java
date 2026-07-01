package org.jesperancinha.sftd.flash.aop;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.springframework.aop.framework.ProxyFactory;

import static org.jesperancinha.console.consolerizer.console.ConsolerizerComposer.title;

public class SpringFlash2Launcher {
    public static void main(String[] args) {
        final var seed = new Seed("Chives");
        final var vase = new Vase();
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.addAdvice(new VaseAdvice());
        proxyFactory.setTarget(vase);

        final var proxy = (Vase) proxyFactory.getProxy();

        final Plant plant = proxy.seed(seed);

        ConsolerizerComposer.outSpace()
                .blue(title("Using local AOP proxies"))
                .brightBlue("Proxy, cut-points and advices")
                .yellow("We started with seed: %s", seed)
                .bgOrange("We then get the following results:")
                .green("Out result is  %s.", plant)
                .green("A Plant with hashcode %s", plant.hashCode())
                .magenta("The 'Seeds are planted!', is part of the advice we use in our proxy.")
                .magenta("It gets executed before calling proceed, which finally represents the call to the 'seed' method.")
                .reset();
    }
}
