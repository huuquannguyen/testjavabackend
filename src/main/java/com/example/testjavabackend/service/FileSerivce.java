package com.example.testjavabackend.service;

import com.example.testjavabackend.exception.StorageException;
import com.example.testjavabackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileSerivce {

    @Value("${upload.path.user}")
    private String path;

    @Autowired
    private UserService userService;

    public String storeFile(MultipartFile file){
        if(file.isEmpty()){
            throw new StorageException("File is empty");
        }
        String fileName = userService.findUser().getId();
        String filePath = path + fileName + "." + file.getContentType().substring("image/".length());
        try {
            var is =file.getInputStream();
            Files.copy(is, Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
        }catch (IOException e){
            var msg = String.format("Failed to store file ", file.getOriginalFilename());
            throw new StorageException(msg, e);
        }
        return filePath;
    }
}
