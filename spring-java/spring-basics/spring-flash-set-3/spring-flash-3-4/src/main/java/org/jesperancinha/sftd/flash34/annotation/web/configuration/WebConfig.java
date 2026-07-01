package org.jesperancinha.sftd.flash34.annotation.web.configuration;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.jesperancinha.console.consolerizer.console.ConsolerizerComposer.title;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"org.jesperancinha.sftd.flash34.annotation.web.controller"})
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void configureDefaultServletHandling(
            final DefaultServletHandlerConfigurer defaultServletHandlerConfigurer) {
        ConsolerizerComposer.outSpace()
                .green(title("DefaultServletHandlerConfigurer"))
                .magenta(defaultServletHandlerConfigurer)
                .reset();
        defaultServletHandlerConfigurer.enable();
    }
}
