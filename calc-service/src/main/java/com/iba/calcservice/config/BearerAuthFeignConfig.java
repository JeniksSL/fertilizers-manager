package com.iba.calcservice.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
public class BearerAuthFeignConfig {
    @Bean
    public RequestInterceptor bearerAuthRequestInterceptor() {
        return requestTemplate -> {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.getPrincipal() instanceof Long) {
                Long userId = (Long) authentication.getPrincipal();

               // requestTemplate.header("Authorization", "Bearer " + jwt.getTokenValue());
            } else {

            }
        };
    }
}
