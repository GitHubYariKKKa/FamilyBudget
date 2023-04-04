package com.Intership.FamilyBudget.dto;

import com.Intership.FamilyBudget.model.Family;
import com.Intership.FamilyBudget.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class FamilyResponseDTO {
    private int id;
    private String name;
    private List<User> users;

    public FamilyResponseDTO(Family family){
        id = family.getId();
        name = family.getName();
        users = family.getUsers();
    }
}
