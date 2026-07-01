package org.jesperancinha.sftd.flash412.beans;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.jesperancinha.sftd.flash412.beans.eating.Beans;
import org.jesperancinha.sftd.flash412.beans.eating.Pork;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.RED;
import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.YELLOW;

@SpringBootApplication
public class SpringFlash412Launcher implements CommandLineRunner {

    private final ApplicationContext applicationContext;

    public SpringFlash412Launcher(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public static void main(String[] args) {
        SpringApplication.run(new Class[]{SpringFlash412Launcher.class}, args);
    }

    @Override
    public void run(String... args) {
        final var beans = (Beans) applicationContext.getBean("beans");
        final var pork = (Pork) applicationContext.getBean("pork");

        YELLOW.printGenericLn(beans.showFood());
        YELLOW.printGenericLn(pork.showFood());

        try {
            applicationContext.getBean("food");
        } catch (Throwable e) {
            RED.printExpectedException("This is what we get if the id is not found. @Component is responsible for attributing the id", e);
        }
        ConsolerizerComposer.outSpace()
                .brightMagenta("Let's make supper.")
                .magenta("We add %s.", pork)
                .brightMagenta("To our %s.", beans)
                .magenta("Tastes good right? It's healthy also!")
                .green("\"I ain't got a thing to prove to you\"")
                .newLine()
                .cyan("The purpose of this exercise is to understand bean Id's")
                .newLine()
                .orange("Trying to get %s will not work", "food")
                .reset();
    }
}