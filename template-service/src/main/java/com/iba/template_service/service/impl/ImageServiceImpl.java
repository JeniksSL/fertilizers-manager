package com.iba.template_service.service.impl;

import com.iba.template_service.repository.ImageRepository;
import com.iba.template_service.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    @Override
    public String save(MultipartFile file, String fileName) {
       try {
           return imageRepository.save(file.getBytes(), fileName);
       } catch (Exception e) {
           throw new RuntimeException(e);
       }

    }

    @Override
    public Resource getImage(String location) {
        try {
            return new ByteArrayResource(imageRepository.findInFileSystem(location));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
