package io.cjf.jacartadministrationback.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/image")
public class ImageController {

    @PostMapping("/upload")
    public String  upload(@RequestParam("image")MultipartFile image){
        return null;
    }
}
