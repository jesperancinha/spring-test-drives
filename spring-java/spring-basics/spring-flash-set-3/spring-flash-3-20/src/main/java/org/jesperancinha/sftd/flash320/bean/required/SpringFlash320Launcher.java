package org.jesperancinha.sftd.flash320.bean.required;

import org.jesperancinha.console.consolerizer.console.Consolerizer;
import org.jesperancinha.sftd.flash320.bean.required.domain.People;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.*;

@SpringBootApplication
public class SpringFlash320Launcher implements CommandLineRunner {

    private final People people;

    private final People peopleXml;

    public SpringFlash320Launcher(
            @Qualifier("people")
            People people,
            @Qualifier("peopleXml")
            People peopleXml) {
        this.people = people;
        this.peopleXml = peopleXml;
    }

    public static void main(String[] args) {
        GREEN.printGenericTitleLn("Service is starting");
        SpringApplication.run(SpringFlash320Launcher.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Consolerizer.printRainbowTitleLn("This is how we help the people -> %s", people);
        MAGENTA.printGenericLn("This always works if the beans are created via annotations.");
        MAGENTA.printGenericLn("However, if we try to get a bean with empty values from an XML file");
        Consolerizer.printRainbowTitleLn("This is how we help the people -> %s", peopleXml);

        ApplicationContext context1 = new ClassPathXmlApplicationContext("bean1.xml");
        final Object bean1 = context1.getBean("peopleXml");

        GREEN.printGenericLn("We can get bean1 -> %s", bean1);

        try {
            ApplicationContext context = new ClassPathXmlApplicationContext("bean2.xml");
        } catch (final BeanCreationException beanCreationException) {
            BRIGHT_GREEN.printExpectedException("The @Required annotation is deprecated, but it still works when reading context beans from files", beanCreationException.getMessage());
            RED.printGenericLn("Note that this only works because this Bean is declared in the bean context file: RequiredAnnotationBeanPostProcessor.");
        }

        GREEN.printGenericLn("But we won't get bean2");
    }
}