package org.jesperancinha.sftd.flash35.aop.security;

import org.jesperancinha.sftd.flash35.aop.security.southern.oracle.SouthernOracle;
import org.jesperancinha.sftd.flash35.aop.security.southern.oracle.SouthernOracleAdvice;
import org.jesperancinha.sftd.flash35.aop.security.southern.oracle.SouthernOracleMessage;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.GREEN;
import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.ORANGE;

@SpringBootApplication
public class SpringFlash35Launcher implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(SpringFlash35Launcher.class, args);
    }

    /**
     * This run method exemplifies how to create runtime advices for our target methods.
     * We take an example and contextualize it in The Neverending Story, where Atreyu just met the Southern Oracle.
     * There, Atreyu must give the Empress a name. Falkor is not allowed to do it and The Nothing definitely not.
     * Our target is a {@link SouthernOracleMessage}
     * Our method {@link org.aspectj.lang.reflect.Advice} is {@link org.springframework.aop.MethodBeforeAdvice}
     * The Southern Oracle only allows one individual to come in and talk to it and is represented by {@link SouthernOracle}
     *
     * @param args The input arguments from the command line
     */
    @Override
    public void run(String... args) {
        SouthernOracle mgr = new SouthernOracle();
        SouthernOracleMessage bean = southernOracleMessage();
        mgr.login("Atreyu");
        bean.giveANewName();
        mgr.logout();
        try {
            mgr.login("Falkor");
            bean.giveANewName();
        } catch (SecurityException securityException) {
            ORANGE.printExpectedException("Falkor is not in the Southern Oracle at this point", securityException.getMessage());
        } finally {
            mgr.logout();
        }
        try {
            bean.giveANewName();
        } catch (SecurityException securityException) {
            ORANGE.printExpectedException("The Nothing has tried to reach the Southern Oracle. We won't let it", securityException.getMessage());
        }

        GREEN.printGenericLn("We can confirm that our method is protected against other users.");
        GREEN.printGenericLn("Atreyu is safe to be the only one to get the message from the Southern Oracle.");
    }

    public SouthernOracleMessage southernOracleMessage() {
        SouthernOracleMessage target = new SouthernOracleMessage();
        SouthernOracleAdvice advice = new SouthernOracleAdvice();
        ProxyFactory factory = new ProxyFactory();
        factory.setTarget(target);
        factory.addAdvice(advice);
        return (SouthernOracleMessage) factory.getProxy();
    }
}
