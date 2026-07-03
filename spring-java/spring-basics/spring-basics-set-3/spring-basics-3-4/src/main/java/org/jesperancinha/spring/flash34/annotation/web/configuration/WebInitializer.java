package org.jesperancinha.spring.flash34.annotation.web.configuration;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import static org.jesperancinha.console.consolerizer.console.ConsolerizerComposer.title;

public class WebInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(jakarta.servlet.ServletContext servletContext) {
        final var annotationConfigWebApplicationContext = new AnnotationConfigWebApplicationContext();
        annotationConfigWebApplicationContext.register(WebConfig.class);
        ConsolerizerComposer.outSpace()
                .green(title("AnnotationConfigWebApplicationContext"))
                .brightBlue(annotationConfigWebApplicationContext)
                .blue("We first create our AnnotationConfigWebApplicationContext: %s", annotationConfigWebApplicationContext.hashCode())
                .magenta(annotationConfigWebApplicationContext)
                .yellow("spring-basics-3-4")
                .reset();
        final var dispatcher = servletContext.addServlet(
                "dispatcher", new DispatcherServlet(annotationConfigWebApplicationContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");

    }
}
