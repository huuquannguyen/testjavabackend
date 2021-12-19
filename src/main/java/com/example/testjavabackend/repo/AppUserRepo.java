package com.example.testjavabackend.repo;

import com.example.testjavabackend.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AppUserRepo extends JpaRepository<AppUser, String> {

    Optional<AppUser> findByUsername(String username);

    @Query("SELECT a FROM AppUser a WHERE a.username = ?#{authentication.name} ")
    AppUser findUser();

}
