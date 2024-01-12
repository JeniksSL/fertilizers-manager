package com.iba.template_service.client;

import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignCustomErrorDecoder implements ErrorDecoder {
    private final ErrorDecoder errorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() == 302) {
            throw new Response302Exception(
                    response.reason(),response);
        }
        return errorDecoder.decode(methodKey, response);
    }
}