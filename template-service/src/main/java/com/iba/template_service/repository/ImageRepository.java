package com.iba.template_service.repository;

import org.springframework.core.io.FileSystemResource;

public interface ImageRepository {

    String save(byte[] content, String imageName) throws Exception;

    byte[] findInFileSystem(String location) throws Exception;
}
