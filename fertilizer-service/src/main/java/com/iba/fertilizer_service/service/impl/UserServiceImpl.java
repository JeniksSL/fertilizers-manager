package com.iba.fertilizer_service.service.impl;

import com.iba.fertilizer_service.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public Long getCurrentUserId() {
        try {
            Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return Long.valueOf(jwt.getClaim("sub"));
        } catch (Exception e) {
            return null;
        }
    }
}
