package com.iba.template_service.facade.impl;

import com.iba.template_service.client.AuthServiceClient;
import com.iba.template_service.facade.ImageFacade;
import com.iba.template_service.service.ImageService;
import com.iba.template_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageFacadeImpl implements ImageFacade {

    private final AuthServiceClient authServiceClient;

    private final ImageService imageService;
    private final UserService userService;

    @Override
    public void save(MultipartFile file) {
        Long userId = userService.getCurrentUserId();
        //String location = authServiceClient.getImageLocation(userId); TODO remove old file (add to late tasks)
        UUID random = UUID.randomUUID();
        String fileName = random.toString();
        String location = imageService.save(file,fileName);
        authServiceClient.attachImageToUser(userService.getCurrentUserId(), location);
    }

    @Override
    public Resource getImage() {
        Long userId = userService.getCurrentUserId();
        String location = authServiceClient.getImageLocation(userId);
        return Optional.of(location).map(imageService::getImage).orElse(null);
    }
}
