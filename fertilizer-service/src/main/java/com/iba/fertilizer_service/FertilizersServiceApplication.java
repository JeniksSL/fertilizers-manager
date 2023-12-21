package com.iba.fertilizer_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FertilizersServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(FertilizersServiceApplication.class, args);
    }

}
