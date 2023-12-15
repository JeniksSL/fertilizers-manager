package com.iba.authservice.initial;



import com.iba.authservice.domain.User;
import com.iba.authservice.repository.UserRepository;
import com.iba.authservice.security.UserPrincipal;
import com.iba.authservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements ApplicationRunner {
    private final UserService userService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public static final String INIT_EMAIL = "initial@gmail.com";
    public static final String INIT_PASSWORD = "initial@gmail.com";

    public static final String INIT_NAME = "User";

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (userRepository.existsByEmail(INIT_EMAIL)) return;
        // create user
        User user = userService.createUser(
                User
                        .builder()
                        .email(INIT_EMAIL)
                        .password(INIT_PASSWORD)
                        .firstName(INIT_NAME)
                        .build()
        ).get();
        user.setIsEnabled(true);
        userService.saveUser(user);
        UserPrincipal userPrincipal = UserPrincipal.create(user);
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(userPrincipal.getUserId(), null, userPrincipal.getAuthorities())
        );
    }


}
