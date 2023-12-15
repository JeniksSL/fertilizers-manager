package com.iba.authservice.service.impl;


import com.iba.authservice.domain.User;
import com.iba.authservice.domain.UserCode;
import com.iba.authservice.exceptions.ConflictException;
import com.iba.authservice.exceptions.EntityNotFoundException;
import com.iba.authservice.repository.UserCodeRepository;
import com.iba.authservice.repository.UserRepository;
import com.iba.authservice.security.UserPrincipal;
import com.iba.authservice.service.UserService;
import com.iba.fertilizersmanager.RoleType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserCodeRepository userCodeRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new ConflictException(String.format("User with email %s already exists", user.getEmail()));
        }
        populateNewUser(user);
        userRepository.save(user);
        sendCodeToUser(user);

        // loading user second time to load user settings
        User createdUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new EntityNotFoundException(User.class));
        //eventPublisher.publishEvent(new UserCreatedEvent(createdUser));
        return Optional.of(createdUser);
    }

    private void sendCodeToUser(User user) {
        UserCode userCode = new UserCode();
        userCode.setUserId(user.getId());
        userCode.setSent(false);
        userCodeRepository.save(userCode);
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    private void populateNewUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setIsEnabled(false);
        user.setIsEmailConfirmed(false);
        user.setRoles(Set.of(RoleType.ROLE_USER));
    }
    @Override
    public Long getCurrentUserId() {
        return ((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();
    }

    @Override
    public Optional<User> getCurrentUser() {
        return userRepository.findById(getCurrentUserId());
    }

    @Override
    public User patch(User user) {
        User current = getCurrentUser().orElseThrow();
        patchUser(current, user);
        return userRepository.save(current);
    }

    @Override
    public void changePassword(User user, String oldPassword, String newPassword) {
        if (oldPassword == null || !passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new RuntimeException("The old password provided by the user doesn't match the real old password");
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    @Override
    public void deleteUser(User user, String oldPassword) {
        if (oldPassword == null || !passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new RuntimeException("The old password provided by the user doesn't match the real old password");
        }
        user.setIsEnabled(false);
        userRepository.save(user);
    }

    @Override
    public void confirmUserByCode(UUID code) {
        UserCode userCode = userCodeRepository.findById(code).orElseThrow();
        User user = userRepository.findById(userCode.getUserId()).orElseThrow();
        activateUser(user);
        userRepository.save(user);
        userCodeRepository.delete(userCode);
    }

    private void activateUser(User user) {
        user.setIsEnabled(true);
        user.setIsEmailConfirmed(true);
    }

    private void patchUser(User current, User user) {

    }
}
