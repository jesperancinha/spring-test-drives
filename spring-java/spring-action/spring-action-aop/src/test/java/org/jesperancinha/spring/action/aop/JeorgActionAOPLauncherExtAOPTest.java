package org.jesperancinha.spring.action.aop;

import org.jesperancinha.spring.action.aop.catchers.BonitoCatcher;
import org.jesperancinha.spring.action.aop.catchers.cod.CodCatcher;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.annotation.DirtiesContext;

/**
 * The {@link EnableAspectJAutoProxy} annotation is not strictly necessary when using {@link org.springframework.boot.autoconfigure.SpringBootApplication}.
 * This is already a part of the {@link org.springframework.boot.autoconfigure.aop.AopAutoConfiguration} class.
 * Since {@link org.springframework.boot.autoconfigure.SpringBootApplication} contains {@link org.springframework.boot.autoconfigure.EnableAutoConfiguration}, this means that the all of these configuration classes will be scanned.
 * <p>
 * It is important to remember at this point that CGLIB proxies are not configured by default
 * We must specify we need them, when manually making our configuration.
 * Spring has a preference for JDK proxies which require an interface.
 **/
@ExtendWith(SpringExtension.class)
@ComponentScan({
        "org.jesperancinha.spring.action.aop.catchers.cod",
        "org.jesperancinha.spring.action.aop.catchers",
        "org.jesperancinha.spring.action.aop.beans",
        "org.jesperancinha.spring.action.aop.aspects"
})
@EnableAspectJAutoProxy(proxyTargetClass = true)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class JeorgActionAOPLauncherExtAOPTest {

    @Autowired
    private BonitoCatcher bonitoCatcher;

    @Autowired
    private CodCatcher codCatcher;

    @Test
    void testContext() {
        bonitoCatcher.catchByHand();
        bonitoCatcher.catchByHandExtra();
        bonitoCatcher.catchWithClaws();
        codCatcher.catchByHand();
    }
}