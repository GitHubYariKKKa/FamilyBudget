package com.Intership.FamilyBudget.forecast;

import lombok.Data;

@Data
public class Clouds {
    private static final String DEFAULT_UNIT = "%";
    private double value;

    private Clouds(double value) {
        this.value = value;
    }

    public static Clouds withValue(double value) {
        if (value < 0 || value > 100)  {
            throw new IllegalArgumentException("Cloudiness value must be in [0, 100] range.");
        }
        return new Clouds(value);
    }
}
