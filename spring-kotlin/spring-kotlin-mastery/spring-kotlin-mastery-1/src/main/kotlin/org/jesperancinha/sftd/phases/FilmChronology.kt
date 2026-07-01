package org.jesperancinha.sftd.phases

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer
import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer.title
import org.springframework.beans.BeansException
import org.springframework.beans.factory.annotation.Value
import org.springframework.beans.factory.config.BeanPostProcessor
import org.springframework.stereotype.Component

@Component
class FilmChronology : BeanPostProcessor {

    @Value("\${management.endpoint.health.enabled}")
    lateinit var healthEnabled: String;


    @Throws(BeansException::class)
    override fun postProcessAfterInitialization(bean: Any, beanName: String): Any? {
        ConsolerizerComposer.outSpace()
            .ln()
            .autoWrite()
            .file("/tmp/spring-kotlin-mastery-1.txt")
            .black()
            .bgOrange("3. postProcessAfterInitialization")
            .red(title("This is phase postProcessAfterInitialization of BeanPostProcessor"))
            .blue("This is bean %s", beanName)
            .reset()
        return bean
    }

    @Throws(BeansException::class)
    override fun postProcessBeforeInitialization(bean: Any, beanName: String): Any? {
        ConsolerizerComposer.outSpace()
            .ln()
            .autoWrite()
            .black()
            .bgYellow("As you can see the bean is created and it is initialized -> %s", healthEnabled)
        ConsolerizerComposer.outSpace()
            .ln()
            .autoWrite()
            .file("/tmp/spring-kotlin-mastery-1.txt")
            .black()
            .bgOrange("2. postProcessBeforeInitialization")
            .red(title("This is phase postProcessBeforeInitialization of BeanPostProcessor"))
            .blue("This is bean %s", beanName)
            .reset()
        return bean
    }
}