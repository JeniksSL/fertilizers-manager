package com.iba.authservice.dto;



import com.iba.fertilizersmanager.dto.core.BaseDto;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto extends BaseDto {

    @NotBlank
    @Email
    private String email;

    private transient String password;

    @NotNull
    private Set<String> roles;


}
