package com.example.testjavabackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String zipCode;
    private String apartment;
    private String street;
    private String district;
    private String city;

    @Override
    public String toString(){
        return zipCode + ", " + apartment + ", " + street + ", " + district + ", " + city;
    }
}
