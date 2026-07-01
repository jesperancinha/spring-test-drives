package org.jesperancinha.sftd.phases

import org.jesperancinha.console.consolerizer.common.ConsolerizerColor
import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer
import org.springframework.beans.factory.annotation.Value
import org.springframework.beans.factory.config.BeanFactoryPostProcessor
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory
import org.springframework.stereotype.Component

@Component
class FilmChronologyFactory : BeanFactoryPostProcessor {

    @Value("\${management.endpoint.health.enabled}")
    lateinit var healthEnabled: String

    override fun postProcessBeanFactory(beanFactory: ConfigurableListableBeanFactory) {
        ConsolerizerComposer.outSpace()
            .ln()
            .black()
            .autoWrite()
            .file("/tmp/spring-kotlin-mastery-1.txt")
            .bgOrange("1. postProcessBeanFactory")
            .red(ConsolerizerComposer.title("This is phase BeanFactoryPostProcessor"))
            .blue("This is bean %s", beanFactory)
            .reset()
        val bean = beanFactory.getBean("movieLinesController")
        ConsolerizerComposer.outSpace()
            .ln()
            .autoWrite()
            .black()
            .bgOrange("This is bean %s. If you notice it has been created", bean)
            .reset()
        try {
            ConsolerizerComposer.outSpace()
                .ln()
                .autoWrite()
                .yellow("As you can see, although the beans are created, they are not initialized -> %s", healthEnabled)
                .reset()
        } catch (e: UninitializedPropertyAccessException) {
            ConsolerizerColor.RED.printExpectedException(
                "In Kotlin is a variable is not initialized, it will result in an exception",
                e
            )
            ConsolerizerComposer.outSpace()
                .ln()
                .autoWrite()
                .yellow("As you can see, although the beans are created, they are not initialized")
                .reset()
        }
        for (beanName in beanFactory.beanDefinitionNames) {
            ConsolerizerComposer.outSpace()
                .ln()
                .autoWrite()
                .file("/tmp/spring-kotlin-mastery-1.txt")
                .brightBlue("Found bean %s", beanName)
                .reset()
        }
    }
}

