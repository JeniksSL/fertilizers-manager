package com.iba.template_service.controller;

import com.iba.template_service.facade.ImageFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;




@RestController
@RequestMapping("/file-server/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageFacade imageFacade;

    @GetMapping
    public @ResponseBody Resource handleFileUpload() {
        return imageFacade.getImage();
    }


    @PostMapping
    public void handleFileUpload(@RequestParam("file") MultipartFile file) {
        imageFacade.save(file);
    }


}
