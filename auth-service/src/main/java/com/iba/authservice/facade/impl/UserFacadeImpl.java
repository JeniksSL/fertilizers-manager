package com.iba.authservice.facade.impl;


import com.iba.authservice.domain.User;
import com.iba.authservice.dto.*;
import com.iba.authservice.facade.UserFacade;
import com.iba.authservice.service.UserService;
import com.iba.fertilizersmanager.dto.core.mapper.DoubleToEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserFacadeImpl implements UserFacade {

    private final UserService userService;

    private final DoubleToEntityMapper<User, UserDto, UserCompact> userMapper;

    @Override
    public UserDto getCurrentUser() {
        return userMapper.toDto(userService.getCurrentUser().orElseThrow());
    }

    @Override
    public UserDto create(UserCompact userCompact) {
        return userMapper.toDto(userService.createUser(userMapper.toEntityFromCompact(userCompact)).orElseThrow());
    }

    @Override
    public UserDto patch(UserDto userDto) {
        return userMapper.toDto(userService.patch(userMapper.toEntity(userDto)));
    }

    @Override
    public void patchPassword(PasswordPatchDto passwordPatchDto) {
        User user = userService.getCurrentUser().orElseThrow();
        userService.changePassword(user, passwordPatchDto.oldPassword(), passwordPatchDto.newPassword());
    }

    @Override
    public void deleteCurrent(String oldPassword) {
        User user = userService.getCurrentUser().orElseThrow();
        userService.deleteUser(user, oldPassword);
    }

    @Override
    public void confirmUserByCode(UUID code) {
        userService.confirmUserByCode(code);
    }
}
