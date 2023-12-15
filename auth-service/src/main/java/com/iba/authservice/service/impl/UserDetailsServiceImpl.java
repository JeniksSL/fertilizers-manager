package com.iba.authservice.service.impl;


import com.iba.authservice.domain.User;
import com.iba.authservice.exceptions.EntityNotFoundException;
import com.iba.authservice.security.UserPrincipal;
import com.iba.authservice.service.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {


    private final UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserByEmail(username).orElseThrow(()->new EntityNotFoundException(User.class));
        return UserPrincipal.create(user);
    }
}
