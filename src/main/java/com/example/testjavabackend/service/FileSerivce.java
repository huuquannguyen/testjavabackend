package com.example.testjavabackend.service;

import com.example.testjavabackend.exception.StorageException;
import com.example.testjavabackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileSerivce {


    private Path imageStoragePath;

    @Autowired
    private UserService userService;

    public FileSerivce(@Value("${upload.path.user}") String path){
        imageStoragePath = Paths.get(path).toAbsolutePath().normalize();
        try {
            Files.createDirectories(imageStoragePath);
        } catch (IOException e) {
            throw new RuntimeException("Issue in creating file directory");
        }
    }

    public String storeFile(MultipartFile file){
        if(file.isEmpty()){
            throw new StorageException("File is empty");
        }
        String fileName = "user_" + userService.findUser().getId() + "." + file.getContentType().substring("image/".length());
        Path filePath = Paths.get(imageStoragePath + "\\" + fileName);
        try {
            var is =file.getInputStream();
            Files.copy(is, filePath, StandardCopyOption.REPLACE_EXISTING);
        }catch (IOException e){
            var msg = String.format("Failed to store file ", file.getOriginalFilename());
            throw new StorageException(msg, e);
        }
        return filePath.toString();
    }
}
