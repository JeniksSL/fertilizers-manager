package com.iba.template_service.client;


import com.iba.template_service.config.ClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "auth-service", url = "http://localhost:8080")
public interface AuthServiceClient {

    @PostMapping("/auth-server/users/image/{id}")
    void attachImageToUser(@PathVariable Long id, String imageName);

    @GetMapping("/auth-server/users/image/{id}")
    String getImageLocation(@PathVariable Long id);
}
