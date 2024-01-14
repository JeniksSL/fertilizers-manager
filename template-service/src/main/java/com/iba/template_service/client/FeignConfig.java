package com.iba.template_service.client;

import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;

public class FeignConfig {

    @Bean
    public ErrorDecoder customErrorDecoder() {
        return new FeignCustomErrorDecoder();
    }


}
