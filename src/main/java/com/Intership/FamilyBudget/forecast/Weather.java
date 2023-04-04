package com.Intership.FamilyBudget.forecast;

import lombok.Data;

@Data
public class Weather {
    private double minTemperature;
    private double maxTemperature;
    private double humidity;
    private double rain;
    private double snow;
    private double clouds;

    public Weather(double minTemperature, double maxTemperature, double humidity, double rain, double snow, double clouds) {
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
        this.humidity = humidity;
        this.rain = rain;
        this.snow = snow;
        this.clouds = clouds;
    }
}
