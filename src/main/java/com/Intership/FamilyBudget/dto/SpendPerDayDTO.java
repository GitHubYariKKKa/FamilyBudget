package com.Intership.FamilyBudget.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SpendPerDayDTO {
    private LocalDate day;
    private long spendMoney;

    public SpendPerDayDTO(LocalDate day, long spendMoney) {
        this.day = day;
        this.spendMoney = spendMoney;
    }
}
