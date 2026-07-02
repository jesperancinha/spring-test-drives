package org.jesperancinha.sftd.flash516.shutdown.controller;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.jesperancinha.console.consolerizer.console.ConsolerizerComposer.quote;


@RestController
@RequestMapping("api")
public class ShutdownController {

    private final ApplicationContext applicationContext;

    public ShutdownController(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @GetMapping("terminator1")
    public void shutdown1() {
        ConsolerizerComposer.outSpace()
                .red("Received shutdown signal from Terminator I")
                .red(quote("Cyborgs don't feel pain. I do. Don't do that again"))
                .red("Shutting down with SpringApplication.exit() ")
                .reset();
        SpringApplication.exit(applicationContext, () -> 0);
    }

    @GetMapping("terminator2")
    public void shutdown2() {
        final var applicationContext = (AbstractApplicationContext) this.applicationContext;
        ConsolerizerComposer.outSpace()
                .red("Received registerShutdownHook signal from Terminator II")
                .red(quote("Hasta la vista, baby"))
                .red("Registering ApplicationContext shutdown hook")
                .red("With SpringBootApplication annotation, we have already registered a shutdown hook")
                .red("The server only shutdowns after calling close()")
                .reset();
        applicationContext.registerShutdownHook();
    }

    @GetMapping("terminator3")
    public void shutdown3() {
        final var applicationContext = (AbstractApplicationContext) this.applicationContext;
        ConsolerizerComposer.outSpace()
                .red("Received close signal from Terminator III")
                .red(quote("I'm unable to comply"))
                .red("Shutting down by calling close() from the ApplicationContext")
                .reset();
        applicationContext.close();
    }
}
