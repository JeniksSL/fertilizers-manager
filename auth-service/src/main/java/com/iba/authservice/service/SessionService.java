package com.iba.authservice.service;



import com.iba.authservice.domain.Session;

import java.util.Optional;
import java.util.UUID;

public interface SessionService {
    Optional<Session> getSessionById(UUID sessionId);

    Optional<Session> createSession(Session session);
}
