package com.iba.template_service.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    String save(MultipartFile file, String fileName);
    Resource getImage(String location);
}
