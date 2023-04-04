package com.Intership.FamilyBudget.forecast;

import lombok.Data;

@Data
public class Humidity {
    private static final String DEFAULT_UNIT = "%";
    private int value;
    private Humidity(int value) {
        this.value = value;
    }

    public static Humidity withValue(int value) {
        if (value < 0 || value > 100)  {
            throw new IllegalArgumentException("Humidity value must be in [0, 100] range.");
        }
        return new Humidity(value);
    }

}
