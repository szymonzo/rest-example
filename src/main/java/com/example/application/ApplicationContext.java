package com.example.application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

import java.util.Locale;

/**
 * Created by szymon on 21.04.16.
 */
@Configuration
public class ApplicationContext {

    @Bean(name = "messageSource")
    ResourceBundleMessageSource resourceBundleMessageSource() {
        ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
        resourceBundleMessageSource.setBasename("api-messages/messages");
        resourceBundleMessageSource.setFallbackToSystemLocale(true);
        return resourceBundleMessageSource;
    }

    @Bean
    FixedLocaleResolver fixedLocaleResolver() {
        FixedLocaleResolver fixedLocaleResolver = new FixedLocaleResolver();
        fixedLocaleResolver.setDefaultLocale(new Locale("en"));
        return fixedLocaleResolver;
    }
}
