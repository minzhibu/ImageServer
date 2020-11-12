package com.lsl.image.service;


import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    String upLoadImage(MultipartFile multipartFile);
}
