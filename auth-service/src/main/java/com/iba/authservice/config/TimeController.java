package com.iba.authservice.config;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@RestController
public class TimeController {

    @GetMapping("/auth-server/time")
    public String retrieveTime() {
        DateTimeFormatter dateTimeFormatter =
                DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime localTime = LocalTime.now();
        return dateTimeFormatter.format(localTime);
    }
}