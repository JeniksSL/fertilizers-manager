package com.iba.template_service.facade;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface ImageFacade {
    void save(MultipartFile file);

    Resource getImage();

}
