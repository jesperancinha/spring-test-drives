package org.jesperancinha.sftd.flash45.context;

import jakarta.servlet.ServletContext;
import org.jesperancinha.console.consolerizer.common.ConsolerizerColor;
import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.time.LocalDateTime;

import static org.jesperancinha.console.consolerizer.console.ConsolerizerComposer.title;

@SpringBootApplication
public class SpringFlash45Launcher extends SpringBootServletInitializer implements ServletContextInitializer, CommandLineRunner {
    @Override
    public void onStartup(ServletContext servletContext) {
        ConsolerizerColor.BLUE.printGenericTitleLn("AppInitializer on %s", LocalDateTime.now());
        ConsolerizerComposer.out(" ")
                .magenta("WebApplicationInitializer won't work be cause it has been disabled as described in\n")
                .yellow("https://github.com/spring-projects/spring-boot/issues/522")
                .magenta(".")
                .blue("This is the reason why we switch to ServletContextInitializer in this example.")
                .toConsoleLn();
        final var context = getContext();
        final ContextLoaderListener contextLoaderListener = new ContextLoaderListener(context);
        ConsolerizerComposer.out(" ")
                .magenta("We don't use this")
                .blue("So instead of using")
                .orange("servletContext.addListener(contextLoaderListener);")
                .blue("we just print the result -> %s", contextLoaderListener.toString())
                .toConsoleLn();
        final var dispatcherServlet = servletContext.addServlet("DispatcherServlet", new DispatcherServlet(context));
        dispatcherServlet.setLoadOnStartup(1);
        dispatcherServlet.addMapping("/app");
    }

    private AnnotationConfigWebApplicationContext getContext() {
        ConsolerizerColor.BLUE.printGenericTitleLn("AnnotationConfigWebApplicationContext on %s", LocalDateTime.now());
        final var context = new AnnotationConfigWebApplicationContext();
        context.setConfigLocation("org.jesperancinha.sftd.flash45.context");
        return context;
    }

    public static void main(String[] args) {
        SpringApplication.run(new Class[]{SpringFlash45Launcher.class}, args);
    }

    @Override
    public void run(String... args) {
        ConsolerizerComposer.outSpace()
                .cyan(title("AnnotationConfigWebApplicationContext and ContextLoaderListener"))
                .magenta("To better understand what this module is about, it's better to think of it with these two principles in mind:")
                .blue("1. We will create a root mapping called /app with the ContextLoaderListener")
                .blue("2. We will change were the spring auto-configuration starts with the AnnotationConfigWebApplicationContext")
                .reset();
    }
}
