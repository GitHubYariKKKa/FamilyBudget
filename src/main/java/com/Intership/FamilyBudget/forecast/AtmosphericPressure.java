package com.Intership.FamilyBudget.forecast;

import lombok.Data;

@Data
public class AtmosphericPressure {
    private static final String DEFAULT_UNIT = "hPa";
    private double value;

    private Double seaLevelValue;
    private Double groundLevelValue;

    private AtmosphericPressure(double value) {
        this.value = value;
    }

    public static AtmosphericPressure withValue(double value) {
        if (value < 0)  {
            throw new IllegalArgumentException("Atmospheric pressure value must be in [0, +∞) range.");
        }
        return new AtmosphericPressure(value);
    }
}
