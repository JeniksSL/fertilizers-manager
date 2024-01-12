package com.iba.template_service.client;

import feign.Response;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class Response302Exception extends RuntimeException {
    private String reason;
    private Response response;

}
