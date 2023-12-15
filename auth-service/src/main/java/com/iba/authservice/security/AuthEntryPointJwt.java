package com.iba.authservice.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

  @Override
  public void commence(final HttpServletRequest request, final HttpServletResponse response, final AuthenticationException authException)
      throws IOException {
    log.error("Bad request error: {}", authException.getMessage());

    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    final Map<String, Object> body = new HashMap<>();
    if (request.getAttribute("expiredRefreshToken") != null) {
      response.setStatus(HttpServletResponse.SC_FORBIDDEN);
      body.put("error", "Access token expired");
    } else {
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      body.put("error", "Unauthorized");
    }
    body.put("message", authException.getMessage());
    body.put("path", request.getServletPath());

    final ObjectMapper mapper = new ObjectMapper();
    mapper.writeValue(response.getOutputStream(), body);  }



}
