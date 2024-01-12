package com.iba.authservice.facade;



import com.iba.authservice.dto.*;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface UserFacade {
    UserDto getCurrentUser();


    UserDto create(UserCompact userCompact);

    UserDto patch(UserDto userPatchDto);

    void patchPassword(PasswordPatchDto passwordPatchDto);

    void deleteCurrent(String oldPassword);

    void confirmUserByCode(UUID code);
    void attachImageToUser(Long id, String imageName);

    String getImageLocation(Long id);
}
