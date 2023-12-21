package com.iba.fertilizer_service.service.impl;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserServiceImpl implements UserService{
    @Override
    public Long getCurrentUserId() {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //jwt.getClaims().forEach((k,v)-> {System.out.println(k); System.out.println(v);});
        return Long.valueOf(jwt.getClaim("sub"));
    }
}
