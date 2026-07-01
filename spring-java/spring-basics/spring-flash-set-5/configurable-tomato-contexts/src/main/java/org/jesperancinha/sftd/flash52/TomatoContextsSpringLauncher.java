package org.jesperancinha.sftd.flash52;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.DelegatingFilterProxyRegistrationBean;
import org.springframework.context.ApplicationContext;

import java.lang.annotation.Annotation;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.*;
import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.BRIGHT_WHITE;
import static org.jesperancinha.console.consolerizer.console.ConsolerizerComposer.out;
import static org.jesperancinha.console.consolerizer.console.ConsolerizerComposer.outSpace;

@SpringBootApplication
public class TomatoContextsSpringLauncher implements CommandLineRunner {

    private final ApplicationContext applicationContext;

    public TomatoContextsSpringLauncher(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public static void main(String[] args) {
        GREEN.printGenericTitleLn("Service is starting");
        SpringApplication.run(TomatoContextsSpringLauncher.class, args);
    }

    @Override
    public void run(String... args) {
        final var securityFilterChainRegistration = applicationContext.getBean("securityFilterChainRegistration", DelegatingFilterProxyRegistrationBean.class);
        final var delegatingFilterProxy = securityFilterChainRegistration.getFilter();
        outSpace()
                .none()
                .orange("This is the DelegatingFilterProxy")
                .magenta(delegatingFilterProxy)
                .newLine()
                .orange("getContextAttribute")
                .brightWhite(delegatingFilterProxy.getContextAttribute())
                .newLine()
                .orange("getEnvironment")
                .yellow(delegatingFilterProxy.getEnvironment())
                .newLine()
                .orange("getFilterConfig")
                .brightWhite(delegatingFilterProxy.getFilterConfig())
                .newLine()
                .orange("Isn't it beautiful?")
                .reset();

        out(" ")
                .yellow("The @SpringBootAnnotation is composed of a lot of other annotations")
                .newLine()
                .green("These are:")
                .newLine()
                .brightGreen(stream(this.getClass().getAnnotations())
                        .map(Annotation::annotationType)
                        .map(Class::getAnnotations)
                        .collect(Collectors.toList()))
                .toConsoleLn();

        stream(this.getClass().getAnnotations()).forEach(annotation -> {
            BRIGHT_BLUE.printGenericLn(annotation);
            stream(annotation.annotationType().getAnnotations())
                    .forEach(annotation1 -> {
                        BRIGHT_GREEN.printGenericLn(annotation1);
                        stream(annotation1.annotationType().getAnnotations())
                                .forEach(BRIGHT_WHITE::printGenericLn);
                    });
        });

        ConsolerizerComposer
                .out(" ")
                .red("Our tomato has grown 🍅")
                .newLine()
                .red("And now we eat it!")
                .newLine()
                .magenta("Please stop the server to see that in action")
                .toConsoleLn();
    }

}