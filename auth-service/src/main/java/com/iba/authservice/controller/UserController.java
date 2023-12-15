package com.iba.authservice.controller;



import com.iba.authservice.dto.*;
import com.iba.authservice.facade.UserFacade;
import com.iba.fertilizersmanager.utils.ControllerUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/auth-server/users")
@RequiredArgsConstructor
public class UserController {
    private final UserFacade userFacade;


    @GetMapping
    public UserDto getCurrentUser() {
        return userFacade.getCurrentUser();
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto create(@Valid @RequestBody UserCompact userCompact,
                                 final BindingResult bindingResult) {
        ControllerUtil.checkBindingResult(bindingResult);
        return userFacade.create(userCompact);
    }

    @PostMapping("/confirm")
    public void confirmUser(UUID code){
        userFacade.confirmUserByCode(code);
    }


    @PatchMapping("/update")
    public UserDto updateUser(@Valid @RequestBody UserDto userDto,
                                        final BindingResult bindingResult) {
        ControllerUtil.checkBindingResult(bindingResult);
        return userFacade.patch(userDto);
    }


    @PatchMapping("/password")
    public void updatePassword(@Valid @RequestBody PasswordPatchDto passwordPatchDto,
                                           final BindingResult bindingResult) {
        ControllerUtil.checkBindingResult(bindingResult);
        userFacade.patchPassword(passwordPatchDto);
    }


    @DeleteMapping("/delete")
    public void delete(String oldPassword) {
        userFacade.deleteCurrent(oldPassword);
    }
}
