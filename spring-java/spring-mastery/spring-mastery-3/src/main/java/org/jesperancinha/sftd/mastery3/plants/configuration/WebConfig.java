package org.jesperancinha.sftd.mastery3.plants.configuration;

import jakarta.servlet.ServletContext;
import org.jesperancinha.sftd.mastery3.plants.controller.GeneralExceptionResolver;
import org.springframework.beans.BeansException;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;


@Configuration
public class WebConfig implements WebMvcConfigurer, ApplicationContextAware, ServletContextAware {

    @Bean
    WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> enableDefaultServlet() {
        return (factory) -> factory.setRegisterDefaultServlet(true);
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public HandlerExceptionResolver handlerExceptionResolver2() {
        SimpleMappingExceptionResolver resolver = new GeneralExceptionResolver();
        resolver.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return resolver;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }

    @Override
    public void setServletContext(ServletContext servletContext) {

    }
}