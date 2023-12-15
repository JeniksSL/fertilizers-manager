package com.iba.fertilizer_service.security.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequest {


    @Email(message = "Email is not valid")
    private String email;
    @NotBlank(message = "Password can not be blank")
    private String password;
}