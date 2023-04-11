package com.Intership.FamilyBudget.dto;

import lombok.Data;

@Data
public class CheckBudgetDTO {
    private boolean isLess;
    private String message;

    public CheckBudgetDTO(boolean isLess, String message) {
        this.isLess = isLess;
        this.message = message;
    }
}
