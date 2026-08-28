package org.jesperancinha.spring.tutorial.persistence.rest.configuration;

import org.jesperancinha.spring.tutorial.persistence.rest.beans.FeelingLoveBean;
import org.jesperancinha.spring.tutorial.persistence.rest.interceptors.FeelingLoveInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {
    private final FeelingLoveBean feelingLoveBean;

    public InterceptorConfiguration(FeelingLoveBean feelingLoveBean) {
        this.feelingLoveBean = feelingLoveBean;
    }

    @Bean
    public FeelingLoveInterceptor feelingLoveInterceptor() {
        return new FeelingLoveInterceptor(feelingLoveBean);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(feelingLoveInterceptor());
    }

}