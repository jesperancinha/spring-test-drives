package org.jesperancinha.sftd.flash515.factories;

import org.jesperancinha.sftd.flash515.factories.events.RedTulipEvent;
import org.jesperancinha.sftd.flash515.factories.events.YellowTulipSpringApplicationEvent;
import org.jesperancinha.sftd.flash515.factories.model.Tulip;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;

@SpringBootApplication
public class SpringFlash515Launcher implements ApplicationRunner {

    static SpringApplication application;
    private final ApplicationContext applicationContext;

    private final ApplicationEventPublisher applicationEventPublisher;

    public SpringFlash515Launcher(ApplicationContext applicationContext, ApplicationEventPublisher applicationEventPublisher) {
        this.applicationContext = applicationContext;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public static void main(String[] args) {
        final SpringApplication springApplication = new SpringApplication(SpringFlash515Launcher.class);
        SpringFlash515Launcher.application = springApplication;
        springApplication.run(args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Tulip tulip = applicationContext.getBean(Tulip.class);
        applicationEventPublisher.publishEvent(new RedTulipEvent(tulip));
        tulip.grow();
        tulip.grow();
        tulip.grow();

        applicationEventPublisher.publishEvent(new YellowTulipSpringApplicationEvent(
                application,
                args.getSourceArgs()));
    }
}
