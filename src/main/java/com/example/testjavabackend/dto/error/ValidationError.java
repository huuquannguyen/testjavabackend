package com.example.testjavabackend.dto.error;

import lombok.Data;

@Data
public class ValidationError {
    private String code;
    private String message;
}
