package com.Intership.FamilyBudget.dto;

import com.Intership.FamilyBudget.model.Family;
import com.Intership.FamilyBudget.model.ShoppingHistory;
import com.Intership.FamilyBudget.model.User;
import com.Intership.FamilyBudget.model.role.Role;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonNaming
public class UserResponseDTO {
    private int id;
    private String name;

    private String surName;

    private String lastName;

    private String email;

    private int budget;

    private String address;

    private String phoneNumber;
    private String role;

    private Family family;

    private List<ShoppingHistory> shoppingHistory;

    public UserResponseDTO(User user){
        id = user.getId();
        name = user.getName();
        surName = user.getSurName();
        lastName = user.getLastName();
        email = user.getEmail();
        budget = user.getBudget();
        role = user.getRole().getRole();
        address = user.getAddress();
        phoneNumber = user.getPhoneNumber();
        family = user.getFamily();
        shoppingHistory = user.getShoppingHistory();
    }
}
