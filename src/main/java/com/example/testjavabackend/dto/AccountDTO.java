package com.example.testjavabackend.dto;

import com.example.testjavabackend.model.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Data
public class AccountDTO {

    @Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{3,8}",
            message = "ID must contain at least one lowercase letter, one uppercase letter, and one number.")
    private String id;

    @NotBlank(message = "Username must not be blank")
    private String username;

    @Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!#^%,|:/<>;\\s`~'\"+=_(){}*.?&-])(.){8,}",
            message = "There must be at least one lowercase letter, one uppercase letter, one number, one special character each")
    private String password;

    @Pattern(regexp = "[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.com", message = "Must be a valid email pattern 'abc@defg.com'")
    private String email;

    @Pattern(regexp = "\\d{3}-\\d{4}-\\d{4}", message = "The pattern is 'XXX-XXXX-XXXX'")
    private String phone;

    @NotNull(message = "Address must not be null")
    private Address addressDetails;
}
