package org.jesperancinha.sftd.flash314.jdk.enhancer;

import org.jesperancinha.console.consolerizer.console.Consolerizer;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.CYAN;
import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.RED;
import static org.jesperancinha.console.consolerizer.console.ConsolerizerGraphs.printUnicornsLn;

public class EnhancerCrum4 {
    public static void main(String[] args) {

        Consolerizer.maxLineCharsGlobal = 200;
        final var bean = new Bean();
        bean.setMessage("I'm a bean!");

        CYAN.printGenericLn("Our bean is -> %s", bean);
        CYAN.printGenericLn("If we check the bean now, we do not have a proxy -> %s", bean.beanState());
        CYAN.printGenericLn("If we make another soup now, we do not have a proxy -> %s", bean.makeProtectedBeanSoup());


        final var proxy =
                (IBean) Proxy.newProxyInstance(IBean.class.getClassLoader(),
                        bean.getClass().getInterfaces(),
                        new java.lang.reflect.InvocationHandler() {
                            @Override
                            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                                if (method.getReturnType() == String.class && method.getName().equals("beanState")) {
                                    return "This is a plant and no soup is made";
                                }
                                if (method.getName().equals("makeBeanSoup")) {
                                    RED.printThrowableAndExit(new RuntimeException("A private method never gets intercepted"));
                                    return "Soup would be made here, but this is never called";
                                }
                                if (method.getReturnType() == String.class && method.getName().equals("makeProtectedBeanSoup")) {
                                    return "We cannot do a protected method. We can still make a soup with public though. JDK proxies need interfaces";
                                } else {
                                    return method.invoke(bean);
                                }
                            }
                        });


        printUnicornsLn(100);
        CYAN.printGenericLn("Our bean is -> %s", proxy);
        CYAN.printGenericLn("If we check the bean now, we do have a proxy -> %s", proxy.beanState());
        CYAN.printGenericLn("If we pirate into the makeSoup we can confirm that it has never changed -> %s", proxy.makeBeanSoupPirate());
        CYAN.printGenericLn("If we make another soup now, we do have a proxy -> %s", proxy.makeProtectedBeanSoup());

    }
}
