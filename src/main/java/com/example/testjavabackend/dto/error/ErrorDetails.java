package com.example.testjavabackend.dto.error;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class ErrorDetails{
    private String title;
    private int status;
    private String detail;
    private long timeStamp;
    private String path;
    private Map<String, List<ValidationError>> errors = new HashMap<>();
}
