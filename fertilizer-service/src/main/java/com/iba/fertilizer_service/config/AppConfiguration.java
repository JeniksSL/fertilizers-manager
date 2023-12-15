package com.iba.fertilizer_service.config;

import org.springframework.context.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@PropertySource({"classpath:not_git.properties"})
public class AppConfiguration {
}
