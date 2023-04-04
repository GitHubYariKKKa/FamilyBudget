package com.Intership.FamilyBudget.forecast;

import lombok.Data;

@Data
public class Snow {

    private static final String DEFAULT_UNIT = "mm";
    private double threeHourLevel;
    private Snow(double threeHourLevel) {
        this.threeHourLevel = threeHourLevel;
    }

    public static Snow withThreeHourLevelValue(double threeHourLevel) {
        if (threeHourLevel < 0) {
            throw new IllegalArgumentException("Snow level value cannot be negative.");
        }
        return new Snow(threeHourLevel);
    }

}
