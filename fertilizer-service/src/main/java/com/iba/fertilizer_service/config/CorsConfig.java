package com.iba.fertilizer_service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Value("${front.url}")
    private String frontUrl;

    @Override
    public void addCorsMappings(final CorsRegistry registry) {

        registry.addMapping("/**")
                .allowedOrigins(frontUrl)
                .allowedMethods("GET", "POST", "OPTIONS", "PUT", "PATCH", "DELETE", "HEAD")
                .allowCredentials(true)
        ;
    }

}