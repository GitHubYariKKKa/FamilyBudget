package com.Intership.FamilyBudget.forecast.enums;

public enum DayTime {
    /**
     * Day value.
     */
    DAY("d"),

    /**
     * Night value.
     */
    NIGHT("n");

    private final String value;

    DayTime(String value) {
        this.value = value;
    }

    /**
     * Returns time of a day value.
     * @return string value
     */
    public String getValue() {
        return value;
    }
}
