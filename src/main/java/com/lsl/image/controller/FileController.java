package com.lsl.image.controller;

import com.lsl.image.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/file")
public class FileController {

    private final FileService fileService;
    @Value("${image.address}")
    private String imageAddress;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/")
    public String uploadImage(@RequestParam("file") MultipartFile multipartFile){
        String s = fileService.upLoadImage(multipartFile);
        return s;
    }
    @RequestMapping(value = "/image/{imageName}",produces = {MediaType.IMAGE_JPEG_VALUE,MediaType.IMAGE_PNG_VALUE,MediaType.IMAGE_GIF_VALUE})
    public  byte[] getImage(@PathVariable("imageName") String imageName){
        FileInputStream inputStream = null;
        byte[] bytes = new byte[0];
        try{
            String imagePath = "F:\\images\\"+ imageName;
            File file = new File(imagePath);
            inputStream = new FileInputStream(file);
            bytes = new byte[inputStream.available()];
            inputStream.read(bytes,0,inputStream.available());
        }catch(Exception e){
            e.getStackTrace();
        }finally{
            if(inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bytes;
    }
}
