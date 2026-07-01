package org.jesperancinha.sftd.action.mvc2.configuration;

import org.jesperancinha.sftd.action.mvc2.resolver.PdfViewResolver;
import org.jesperancinha.sftd.action.mvc2.resolver.TextViewResolver;
import org.jesperancinha.sftd.action.mvc2.view.PdfView;
import org.jesperancinha.sftd.action.mvc2.view.TextView;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.accept.ContentNegotiationManagerFactoryBean;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;

import java.util.Arrays;
import java.util.Properties;

import static org.springframework.http.MediaType.*;

@Configuration
public class BookieCookieConfiguration {

    @Bean
    public ContentNegotiatingViewResolver viewResolver(){
        final var contentNegotiationManagerFactoryBean = new ContentNegotiationManagerFactoryBean();

        contentNegotiationManagerFactoryBean.setDefaultContentType(TEXT_HTML);
        contentNegotiationManagerFactoryBean.setIgnoreAcceptHeader(true);
        contentNegotiationManagerFactoryBean.setFavorParameter(false);
        contentNegotiationManagerFactoryBean.setFavorPathExtension(true);
        final var mediaTypes = new Properties();
        mediaTypes.setProperty("txt", TEXT_PLAIN_VALUE);
        mediaTypes.setProperty("pdf", APPLICATION_PDF_VALUE);
        contentNegotiationManagerFactoryBean.setMediaTypes(
                mediaTypes
        );

        final var contentNegotiatingViewResolver = new ContentNegotiatingViewResolver();
        contentNegotiatingViewResolver.setContentNegotiationManager(contentNegotiationManagerFactoryBean.getObject());
        contentNegotiatingViewResolver.setOrder(-1);
        final var textView = new TextView();
        final var pdfView = new PdfView();
        contentNegotiatingViewResolver.setDefaultViews(Arrays.asList(textView, pdfView));
        final var textViewResolver = new TextViewResolver();
        final var pdfViewResolver = new PdfViewResolver();
        contentNegotiatingViewResolver.setViewResolvers(Arrays.asList(textViewResolver, pdfViewResolver));

        return contentNegotiatingViewResolver;
    }
}
