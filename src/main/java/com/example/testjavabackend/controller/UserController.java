package com.example.testjavabackend.controller;

import com.example.testjavabackend.model.AppUser;
import com.example.testjavabackend.service.FileSerivce;
import com.example.testjavabackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private FileSerivce fileSerivce;

    @GetMapping("/users")
    public List<AppUser> getAllUsers(){
        return userService.findUsers();
    }

    @GetMapping("/user")
    public AppUser getUser(){
        return userService.findUser();
    }

    @PutMapping("/user/update")
    public AppUser updateUser(@RequestBody AppUser appUser){
        return userService.updateUser(appUser);
    }

    @DeleteMapping("/users/delete/{id}")
    public void deleteUser(@PathVariable String id){
        userService.deleteUser(id);
    }

    @PostMapping("/user/uploadEmoji")
    public AppUser uploadEmoji(@RequestParam MultipartFile emoji){
        String emojiPath = fileSerivce.storeFile(emoji);
        AppUser user = userService.findUser();
        user.setEmoji(emojiPath);
        AppUser userUploaded = userService.updateUser(user);
        return userUploaded;
    }
}
