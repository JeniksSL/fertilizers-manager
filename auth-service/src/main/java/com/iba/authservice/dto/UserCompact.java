package com.iba.authservice.dto;


import com.iba.fertilizersmanager.dto.core.CompactDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserCompact extends CompactDto {

    @NotBlank(message = "Email can not be blank")
    @Email(message = "Email is not valid")
    private String email;
    @NotBlank(message = "Password can not be blank")
    private String password;

    private String confirmedPassword;

}
