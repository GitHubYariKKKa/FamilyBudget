package com.Intership.FamilyBudget.forecast;

import lombok.Data;

@Data
public class Rain {
    private static final String DEFAULT_UNIT = "mm";
    private double threeHourLevel;
    private Rain(double threeHourLevel) {
        this.threeHourLevel = threeHourLevel;
    }

    public static Rain withThreeHourLevelValue(double threeHourLevel) {
        if (threeHourLevel < 0) {
            throw new IllegalArgumentException("Rain level value cannot be negative.");
        }
        return new Rain(threeHourLevel);
    }

}
