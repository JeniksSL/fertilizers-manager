package com.iba.authservice.service;


import com.iba.authservice.domain.User;

import java.util.Optional;
import java.util.UUID;

public interface UserService {
    Optional<User> getUserByEmail(String email);
    Optional<User> createUser(User user);
    void saveUser(User user);
    public Long getCurrentUserId();
    Optional<User> getCurrentUser();
    User patch(User user);
    void changePassword(User user, String oldPassword, String newPassword);
    void deleteUser(User user, String oldPassword);

    void confirmUserByCode(UUID code);

    void attachImageToUser(Long id, String imageName);

    String getImageLocation(Long id);
}
