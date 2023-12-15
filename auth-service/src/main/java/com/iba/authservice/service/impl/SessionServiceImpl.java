package com.iba.authservice.service.impl;


import com.iba.authservice.domain.Session;
import com.iba.authservice.repository.SessionRepository;
import com.iba.authservice.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {

    private  final SessionRepository sessionRepository;
    @Override
    public Optional<Session> getSessionById(UUID sessionId) {
        return sessionRepository.findById(sessionId);
    }

    @Override
    public Optional<Session> createSession(Session session) {
        return Optional.of(sessionRepository.save(session));
    }
}
