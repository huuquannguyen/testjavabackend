package com.example.testjavabackend.service;

import com.example.testjavabackend.model.AppUser;
import com.example.testjavabackend.repo.AppUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JPAUserDetailService implements UserDetailsService {

    @Autowired
    private AppUserRepo appUserRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> u = appUserRepo.findByUsername(username);
        if(u.isPresent()){
            AppUser user = u.get();
            List<GrantedAuthority> authorities = new ArrayList<>();
            user.getRoles().forEach(r -> authorities.add(new SimpleGrantedAuthority(r.getName())));
            return new User(user.getUsername(), user.getPassword(), authorities);
        }else{
            throw new UsernameNotFoundException("Bad credencials");
        }
    }
}
