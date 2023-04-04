package com.Intership.FamilyBudget.forecast;

import lombok.Data;

@Data
public class Wind {
    private double speed;
    private Double degrees;
    private String unit;

    /**
     * Instantiates a new Wind.
     *
     * @param speed the speed
     * @param unit  the unitSystem
     */
    private Wind(double speed, String unit) {
        this.speed = speed;
        this.unit = unit;
    }

    /**
     * Creates {@link Wind} object with correctness check
     * @param speed the wind
     * @param unit the unitSystem
     * @return created wind object
     */
    public static Wind withValue(double speed, String unit) {
        if (speed < 0) {
            throw new IllegalArgumentException("Wind speed value must be in positive or zero.");
        }
        if (unit == null) {
            throw new IllegalArgumentException("Unit must be set.");
        }
        return new Wind(speed, unit);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Wind)) return false;

        Wind wind = (Wind) o;

        if (Double.compare(wind.getSpeed(), getSpeed()) != 0) return false;
        if (getDegrees() != null ? !getDegrees().equals(wind.getDegrees()) : wind.getDegrees() != null) return false;
        return getUnit() != null ? getUnit().equals(wind.getUnit()) : wind.getUnit() == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(getSpeed());
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + (getDegrees() != null ? getDegrees().hashCode() : 0);
        result = 31 * result + (getUnit() != null ? getUnit().hashCode() : 0);
        return result;
    }
}
