package com.iba.authservice.security;


import com.iba.authservice.domain.User;
import com.iba.fertilizersmanager.RoleType;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@RequiredArgsConstructor
@Getter
@Setter
public class UserPrincipal implements UserDetails {

    private final Long userId;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;
    private final boolean isEnabled;

    public static UserPrincipal create(final User user) {

        return
                new UserPrincipal(
                        user.getId(),
                        user.getPassword(),
                        user.getRoles().stream().map(RoleType::name).map(SimpleGrantedAuthority::new).toList(),
                        user.getIsEnabled()
                );
    }


    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userId.toString();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
}
