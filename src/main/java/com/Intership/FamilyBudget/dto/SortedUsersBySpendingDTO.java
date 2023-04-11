package com.Intership.FamilyBudget.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SortedUsersBySpendingDTO {
    private String name;
    private long moneySpend;

    private LocalDate day;

    public SortedUsersBySpendingDTO(String name, long moneySpend, LocalDate day) {
        this.name = name;
        this.moneySpend = moneySpend;
        this.day = day;
    }
}
