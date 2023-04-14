package com.Intership.FamilyBudget.dto;

import com.Intership.FamilyBudget.model.Family;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonNaming
public class FamilyRequestDTO {
    private String name;
    private int budget;
    private int actualBudget;

    private int createdBy;

    public FamilyRequestDTO(Family family){
        name = family.getName();
        budget = family.getBudget();
        actualBudget = family.getActualBudget();
    }

    @Override
    public String toString() {
        return "FamilyRequestDTO{" +
                "name='" + name + '\'' +
                ", budget=" + budget +
                '}';
    }
}
