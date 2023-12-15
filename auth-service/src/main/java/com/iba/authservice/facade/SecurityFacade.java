package com.iba.authservice.facade;

import jakarta.servlet.http.HttpServletResponse;

public interface SecurityFacade {
    void generateRefreshToken(String refreshToken, HttpServletResponse response);

    void logout(HttpServletResponse response);
}
