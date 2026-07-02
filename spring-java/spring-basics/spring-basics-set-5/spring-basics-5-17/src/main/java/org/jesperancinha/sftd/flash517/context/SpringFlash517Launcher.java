package org.jesperancinha.sftd.flash517.context;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.springframework.beans.factory.groovy.GroovyBeanDefinitionReader;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

@SpringBootApplication
public class SpringFlash517Launcher implements ApplicationRunner {

    private final ApplicationContext applicationContext;

    public SpringFlash517Launcher(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringFlash517Launcher.class, args);

    }

    @Override
    public void run(ApplicationArguments args) {
        new XmlBeanDefinitionReader((GenericApplicationContext) applicationContext).loadBeanDefinitions("classpath:flash517.xml");
        new GroovyBeanDefinitionReader((GenericApplicationContext) applicationContext).loadBeanDefinitions("classpath:flash517.groovy");
        new PropertiesBeanDefinitionReader((GenericApplicationContext) applicationContext).loadBeanDefinitions("classpath:flash517.properties");
        final var golfFish = (Fish) applicationContext.getBean("goldFish");
        final var fishService = applicationContext.getBean("fishService");
        final var fish = applicationContext.getBean("fish");
        final var fish1 = applicationContext.getBean("fish1");
        ConsolerizerComposer.outSpace()
                .blue("We caught a fish!")
                .blue(golfFish)
                .green("And it has a service")
                .green(fishService)
                .magenta("And we have another fish")
                .magenta(fish1)
                .yellow("And yet another")
                .yellow(fish)
                .reset();

    }
}
