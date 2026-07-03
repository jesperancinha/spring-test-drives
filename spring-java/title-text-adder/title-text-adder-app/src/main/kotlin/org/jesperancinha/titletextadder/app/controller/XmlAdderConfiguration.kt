package org.jesperancinha.titletextadder.app.controller

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.lang.NonNull
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.resource.EncodedResourceResolver
import org.springframework.web.servlet.view.InternalResourceViewResolver
import org.springframework.web.servlet.view.JstlView


@Configuration
class XmlAdderConfiguration : WebMvcConfigurer {
    override fun configureViewResolvers(registry: ViewResolverRegistry): Unit =
        run {
            InternalResourceViewResolver()
                .apply {
                    setViewClass(JstlView::class.java)
                    setPrefix("/WEB-INF/jsp/")
                    setSuffix(".jsp")
                }.also { registry.viewResolver(it) }

        }

    @Bean
    fun webMvcConfigurer(): WebMvcConfigurer? {
        return object : WebMvcConfigurer {
            override fun addResourceHandlers(
                @NonNull registry: ResourceHandlerRegistry,
            ) {
                registry.addResourceHandler("/js/**")
                    .addResourceLocations("classpath:/js/")
                    .resourceChain(false)
                    .addResolver(EncodedResourceResolver())
            }
        }
    }
}