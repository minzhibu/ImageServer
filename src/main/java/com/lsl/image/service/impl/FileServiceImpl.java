package com.lsl.image.service.impl;

import com.lsl.image.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String upLoadImage(MultipartFile multipartFile) {
        String originalFilename = multipartFile.getOriginalFilename();
        String imageType = null;
        if(originalFilename != null){
            imageType = originalFilename.substring(originalFilename.lastIndexOf("."));
        }
        File parentFile = new File("F:\\images\\");
        if(!parentFile.exists()){
            parentFile.mkdirs();
        }
        String fileName = UUID.randomUUID().getMostSignificantBits()+ imageType;
        File file = new File("F:\\images\\" + fileName);
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "/file/image/"+fileName;
    }
}
