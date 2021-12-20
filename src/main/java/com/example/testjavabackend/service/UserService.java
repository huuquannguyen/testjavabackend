package com.example.testjavabackend.service;

import com.example.testjavabackend.dto.AccountDTO;
import com.example.testjavabackend.model.AppUser;
import com.example.testjavabackend.repo.AppUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private AppUserRepo appUserRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AppUser addUser(AppUser user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return appUserRepo.save(user);
    }


    public List<AppUser> findUsers(){
        return appUserRepo.findAll();
    }

    public AppUser findUser(){
        return appUserRepo.findUser();
    }

    @PreAuthorize("#accountDTO.username == authentication.name")
    public AppUser updateUser(AccountDTO accountDTO){
        AppUser user = new AppUser(accountDTO);
        return appUserRepo.save(user);
    }

    @PreAuthorize("#user.username == authentication.name")
    public String uploadEmoji(AppUser user){
        AppUser appUser = appUserRepo.save(user);
        return appUser.getEmoji();
    }

    public void deleteUser(String id){
        appUserRepo.deleteById(id);
    }


}
