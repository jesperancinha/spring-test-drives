package org.jesperancinha.sftd.flash510.bean.initialization;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.concurrent.atomic.AtomicInteger;

@SpringBootApplication
public class SpringFlash510Launcher implements CommandLineRunner {

    private final ApplicationContext applicationContext;

    public static AtomicInteger atomicInteger = new AtomicInteger(1);

    public SpringFlash510Launcher(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringFlash510Launcher.class, args);
    }

    @Override
    public void run(String... args) {
        final var bf = new DefaultListableBeanFactory(applicationContext);
        final var blackBean = bf.getBean("blackBean");
        ConsolerizerComposer.outSpace()
                .yellow("We have gotten the %s by using the DefaultListableBeanFactory", blackBean);
    }
}
