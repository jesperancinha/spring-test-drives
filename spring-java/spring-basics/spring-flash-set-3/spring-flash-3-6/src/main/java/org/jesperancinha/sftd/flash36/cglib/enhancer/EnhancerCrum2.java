package org.jesperancinha.sftd.flash36.cglib.enhancer;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.InvocationHandler;

import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.CYAN;
import static org.jesperancinha.console.consolerizer.console.ConsolerizerGraphs.printUnicornsLn;

public class EnhancerCrum2 {
    public static void main(String[] args) {

        final var tomato = new Tomato();
        tomato.setMessage("I'm a tomato!");

        CYAN.printGenericLn("Our tomato is -> %s", tomato);
        CYAN.printGenericLn("If we pick the tomato now, we do not have a proxy -> %s", tomato.pickupTomato());
        CYAN.printGenericLn("Can we make ketchup -> %s", tomato.makeKetchup());


        final var enhancer = new Enhancer();
        enhancer.setSuperclass(Tomato.class);
        enhancer.setCallback((InvocationHandler) (proxy, method, args1) -> {
            if (method.getReturnType() == String.class && method.getName().equals("pickupTomato")) {
                return "You've opened the box and finally took me out!";
            }
            if (method.getReturnType() == String.class && method.getName().equals("makeKetchup")) {
                return "You've made ketchup!";
            } else {
                return method.invoke(tomato);
            }
        });
        final var proxy = (Tomato) enhancer.create();

        printUnicornsLn(100);
        CYAN.printGenericLn("Our tomato is -> %s", proxy);
        CYAN.printGenericLn("If we pick the tomato now, have a proxy -> %s", proxy.pickupTomato());
        CYAN.printGenericLn("If we try to make ketchup -> %s", proxy.makeKetchup());
    }
}
