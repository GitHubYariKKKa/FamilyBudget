package com.Intership.FamilyBudget.dto;

import lombok.Data;

@Data
public class SortedUsersBySpendingDTO {
    private String name;
    private long money_spend;

    public SortedUsersBySpendingDTO(String name, long money_spend) {
        this.name = name;
        this.money_spend = money_spend;
    }
}
