package com.iba.authservice.facade.impl;


import com.iba.authservice.facade.SecurityFacade;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

@Service
public class SecurityFacadeImpl implements SecurityFacade {
    @Override
    public void generateRefreshToken(String refreshToken, HttpServletResponse response) {

    }

    @Override
    public void logout(HttpServletResponse response) {

    }
}
