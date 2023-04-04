package com.Intership.FamilyBudget.forecast;

import lombok.Data;

@Data
public class Temperature {
    private double value;
    private Double maxTemperature;
    private Double minTemperature;
    private Double feelsLike;
    private String unit;

    private Temperature(double value, String unit) {
        this.value = value;
        this.unit = unit;
    }

    public static Temperature withValue(double value, String unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit must be set.");
        }
        return new Temperature(value, unit);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Temperature)) return false;

        Temperature that = (Temperature) o;

        if (Double.compare(that.getValue(), getValue()) != 0) return false;
        if (getMaxTemperature() != null ? !getMaxTemperature().equals(that.getMaxTemperature()) : that.getMaxTemperature() != null)
            return false;
        if (getMinTemperature() != null ? !getMinTemperature().equals(that.getMinTemperature()) : that.getMinTemperature() != null)
            return false;
        if (getFeelsLike() != null ? !getFeelsLike().equals(that.getFeelsLike()) : that.getFeelsLike() != null)
            return false;
        return getUnit() != null ? getUnit().equals(that.getUnit()) : that.getUnit() == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(getValue());
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + (getMaxTemperature() != null ? getMaxTemperature().hashCode() : 0);
        result = 31 * result + (getMinTemperature() != null ? getMinTemperature().hashCode() : 0);
        result = 31 * result + (getFeelsLike() != null ? getFeelsLike().hashCode() : 0);
        result = 31 * result + (getUnit() != null ? getUnit().hashCode() : 0);
        return result;
    }
}