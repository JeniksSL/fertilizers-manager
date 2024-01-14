package com.iba.template_service.controller;

import com.iba.template_service.facade.ImageFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("/file-server/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageFacade imageFacade;

    @GetMapping(produces = MediaType.MULTIPART_MIXED_VALUE)
    public @ResponseBody Resource handleFileUpload() {
        return imageFacade.getImage();
    }


    @PostMapping(produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody Resource handleFileUpload(@RequestParam("file") MultipartFile file) {
        imageFacade.save(file);
        try {
            return new ByteArrayResource(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
