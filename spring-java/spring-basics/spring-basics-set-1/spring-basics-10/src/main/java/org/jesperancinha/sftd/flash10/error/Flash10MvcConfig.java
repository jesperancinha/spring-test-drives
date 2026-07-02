package org.jesperancinha.sftd.flash10.error;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.BLUE;

@Configuration
@EnableWebMvc
@ComponentScan
@Profile("prod")
public class Flash10MvcConfig implements WebMvcConfigurer {

    @Bean
    public ViewResolver getViewResolver() {
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        internalResourceViewResolver.setViewClass(JstlView.class);
        internalResourceViewResolver.setPrefix("/WEB-INF/jsp/");
        internalResourceViewResolver.setSuffix(".jsp");
        BLUE.printGenericLn("Initializing viewResolver -> %s", internalResourceViewResolver);
        return internalResourceViewResolver;
    }
}