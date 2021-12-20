package com.example.testjavabackend.controller;

import com.example.testjavabackend.dto.AccountDTO;
import com.example.testjavabackend.model.AppUser;
import com.example.testjavabackend.model.Role;
import com.example.testjavabackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

@RestController
public class AccountController {

    @Autowired
    private UserService userService;

    @Value("${upload.path.user}")
    private String emojiPath;

    @PostMapping("/signup")
    public AppUser signup(@Valid @RequestBody AccountDTO accountDTO){
        Path path = Paths.get(emojiPath).toAbsolutePath().normalize();
        AppUser appUser = new AppUser(accountDTO);
        Set<Role> userRole = new HashSet<>();
        userRole.add(new Role(1L, "role_user"));
        appUser.setRoles(userRole);
        appUser.setEmoji(path + "/defaultEmoji.png");
        AppUser user = userService.addUser(appUser);
        return user;
    }


}
