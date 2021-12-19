package com.example.testjavabackend.model;

import com.example.testjavabackend.dto.AccountDTO;
import com.example.testjavabackend.dto.Address;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Entity
@Data
@Table(name = "appuser")
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {

    @Id
    private String id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String address;
    private String emoji;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public AppUser(AccountDTO accountDTO){
        this.id = accountDTO.getId();
        this.username = accountDTO.getUsername();
        this.password = accountDTO.getPassword();
        this.email = accountDTO.getEmail();
        this.phone = accountDTO.getPhone();
        this.address = accountDTO.getAddressDetails().toString();
    }
}
