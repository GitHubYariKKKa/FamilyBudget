package com.Intership.FamilyBudget.dto;

import com.Intership.FamilyBudget.model.Family;
import com.Intership.FamilyBudget.model.User;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonNaming
public class FamilyResponseDTO {
    private int id;
    private String name;
    private int budget;
    private int membersCount;
    private List<User> users;

    private int actualBudget;

    public FamilyResponseDTO(Family family){
        id = family.getId();
        name = family.getName();
        budget = family.getBudget();
        membersCount = family.getUsers().size();
        users = family.getUsers();
        actualBudget = family.getActualBudget();
    }
}
