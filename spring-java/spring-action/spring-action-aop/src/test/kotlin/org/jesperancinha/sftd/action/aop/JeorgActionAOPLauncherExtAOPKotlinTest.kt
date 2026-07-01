package org.jesperancinha.sftd.action.aop

import org.jesperancinha.sftd.action.aop.catchers.BonitoCatcher
import org.jesperancinha.sftd.action.aop.catchers.cod.CodCatcher
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.test.context.junit.jupiter.SpringExtension

/**
 * The [EnableAspectJAutoProxy] annotation is not strictly necessary when using [org.springframework.boot.autoconfigure.SpringBootApplication].
 * This is already a part of the [org.springframework.boot.autoconfigure.aop.AopAutoConfiguration] class.
 * Since [org.springframework.boot.autoconfigure.SpringBootApplication] contains [org.springframework.boot.autoconfigure.EnableAutoConfiguration], this means that the all of these configuration classes will be scanned.
 *
 *
 * It is important to remember at this point that CGLIB proxies are not configured by default
 * We must specify we need them, when manually making our configuration.
 * Spring has a preference for JDK proxies which require an interface.
 */
@ExtendWith(SpringExtension::class)
@ComponentScan(
    "org.jesperancinha.sftd.action.aop.catchers.cod",
    "org.jesperancinha.sftd.action.aop.catchers",
    "org.jesperancinha.sftd.action.aop.beans",
    "org.jesperancinha.sftd.action.aop.aspects"
)
@EnableAspectJAutoProxy(proxyTargetClass = true)
internal class JeorgActionAOPLauncherExtAOPKotlinTest @Autowired constructor(
    private val bonitoCatcher: BonitoCatcher,
    private val codCatcher: CodCatcher,
) {

    @Test
    fun testContext() {
        bonitoCatcher.catchByHand()
        bonitoCatcher.catchByHandExtra()
        bonitoCatcher.catchWithClaws()
        codCatcher.catchByHand()
    }
}