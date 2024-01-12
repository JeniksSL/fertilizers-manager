package com.iba.template_service.repository;

import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Repository;

import java.nio.file.*;

@Repository
public class ImageFileSystemRepository implements ImageRepository {
    String RESOURCES_DIR ="C:/STUDYIBA/IdeaProjects/fertilizers-manager/template-service/";


    public String save(byte[] content, String imageName) throws Exception {
        Path newFile = Paths.get(RESOURCES_DIR+imageName);
        Files.createDirectories(newFile.getParent());
        Files.write(newFile, content);
        return newFile.toAbsolutePath()
                .toString();
    }

    public byte[] findInFileSystem(String location) throws Exception {

        return new FileSystemResource(Paths.get(location)).getContentAsByteArray();

    }
}
