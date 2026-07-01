package org.jesperancinha.sftd.flash36.cglib.enhancer;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.FixedValue;

import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.CYAN;
import static org.jesperancinha.console.consolerizer.console.ConsolerizerGraphs.printUnicornsLn;

public class EnhancerCrum {
    public static void main(String[] args) {
        final var tomato = new Tomato();
        tomato.setMessage("I'm a tomato!");

        CYAN.printGenericLn("Our tomato is -> %s", tomato);
        CYAN.printGenericLn("If we pick the tomato now, we do not have a proxy -> %s", tomato.pickupTomato());
        CYAN.printGenericLn("Can we make ketchup -> %s", tomato.makeKetchup());

        final var enhancer = new Enhancer();
        enhancer.setSuperclass(Tomato.class);
        enhancer.setCallback((FixedValue) () -> "You've opened the box and finally took me out!");
        final var proxy = (Tomato) enhancer.create();

        printUnicornsLn(100);
        CYAN.printGenericLn("Our tomato is -> %s", proxy);
        CYAN.printGenericLn("If we pick the tomato now, have a proxy -> %s", proxy.pickupTomato());
        CYAN.printGenericLn("If we try to make ketchup we get a strange message -> %s", proxy.makeKetchup());
    }
}
