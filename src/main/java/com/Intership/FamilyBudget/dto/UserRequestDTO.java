package com.Intership.FamilyBudget.dto;

import com.Intership.FamilyBudget.model.User;
import com.Intership.FamilyBudget.model.role.Role;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonNaming
public class UserRequestDTO {
    private String name;

    private String surName;

    private String lastName;

    private String email;

    private String password;

    private int budget;

    private String role;

    public UserRequestDTO(User user){
        name = user.getName();
        surName = user.getSurName();
        lastName = user.getLastName();
        email = user.getEmail();
        password = user.getPassword();
        budget = user.getBudget();
        role = user.getRole().getRole();
    }

}
