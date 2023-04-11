package com.Intership.FamilyBudget.dto;

import com.Intership.FamilyBudget.model.User;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Pattern;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;

@Data
@NoArgsConstructor
@JsonNaming
public class UserRequestDTO{
    @Pattern(regexp = "[A-Z][a-z]+",
            message = "Must start with a capital letter followed by one or more lowercase letters")
    private String name;
    @Pattern(regexp = "[A-Z][a-z]+",
            message = "Must start with a capital letter followed by one or more lowercase letters")
    private String surName;
    @Pattern(regexp = "[A-Z][a-z]+",
            message = "Must start with a capital letter followed by one or more lowercase letters")
    private String lastName;
    @Pattern(regexp = "[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}", message = "Must be a valid e-mail address")
    private String email;
    private String address;
    @Pattern(regexp = "\\+38\\d{10}",
            message = "Must start with '+38' ")
    private String phoneNumber;

    //private MultipartFile profilePhoto;
    private String postalIndex;
    private String password;

    public UserRequestDTO(User user){
        name = user.getName();
        surName = user.getSurName();
        lastName = user.getLastName();
        email = user.getEmail();
        password = user.getPassword();
        address = user.getAddress();
        phoneNumber = user.getPhoneNumber();
        postalIndex = user.getPostalIndex();
    }

    @Override
    public String toString() {
            return "UserRequestDTO{" +
                    "name='" + name + '\'' +
                    ", surName='" + surName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", email='" + email + '\'' +
                    ", address='" + address + '\'' +
                    ", phoneNumber='" + phoneNumber + '\'' +
                    ", postalIndex='" + postalIndex + '\'' +
                    ", password='" + password + '\'' +
                    '}';
    }
}
