package com.Intership.FamilyBudget.forecast;

import com.Intership.FamilyBudget.forecast.enums.DayTime;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WeatherResponse {
    private LocalDateTime forecastTime;

    private WeatherState weatherState;
    private Temperature temperature;
    private AtmosphericPressure atmosphericPressure;
    private Humidity humidity;

    private Wind wind;
    private Rain rain;
    private Snow snow;
    private Clouds clouds;

    private String forecastTimeISO;
    private DayTime dayTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WeatherResponse)) return false;

        WeatherResponse weather = (WeatherResponse) o;

        if (getForecastTime() != null ? !getForecastTime().equals(weather.getForecastTime()) : weather.getForecastTime() != null)
            return false;
        if (getWeatherState() != null ? !getWeatherState().equals(weather.getWeatherState()) : weather.getWeatherState() != null)
            return false;
        if (getTemperature() != null ? !getTemperature().equals(weather.getTemperature()) : weather.getTemperature() != null)
            return false;
        if (getAtmosphericPressure() != null ? !getAtmosphericPressure().equals(weather.getAtmosphericPressure()) : weather.getAtmosphericPressure() != null)
            return false;
        if (getHumidity() != null ? !getHumidity().equals(weather.getHumidity()) : weather.getHumidity() != null)
            return false;
        if (getWind() != null ? !getWind().equals(weather.getWind()) : weather.getWind() != null) return false;
        if (getRain() != null ? !getRain().equals(weather.getRain()) : weather.getRain() != null) return false;
        if (getSnow() != null ? !getSnow().equals(weather.getSnow()) : weather.getSnow() != null) return false;
        if (getClouds() != null ? !getClouds().equals(weather.getClouds()) : weather.getClouds() != null) return false;
        if (getForecastTimeISO() != null ? !getForecastTimeISO().equals(weather.getForecastTimeISO()) : weather.getForecastTimeISO() != null)
            return false;
        return getDayTime() == weather.getDayTime();
    }

    @Override
    public int hashCode() {
        int result = getForecastTime() != null ? getForecastTime().hashCode() : 0;
        result = 31 * result + (getWeatherState() != null ? getWeatherState().hashCode() : 0);
        result = 31 * result + (getTemperature() != null ? getTemperature().hashCode() : 0);
        result = 31 * result + (getAtmosphericPressure() != null ? getAtmosphericPressure().hashCode() : 0);
        result = 31 * result + (getHumidity() != null ? getHumidity().hashCode() : 0);
        result = 31 * result + (getWind() != null ? getWind().hashCode() : 0);
        result = 31 * result + (getRain() != null ? getRain().hashCode() : 0);
        result = 31 * result + (getSnow() != null ? getSnow().hashCode() : 0);
        result = 31 * result + (getClouds() != null ? getClouds().hashCode() : 0);
        result = 31 * result + (getForecastTimeISO() != null ? getForecastTimeISO().hashCode() : 0);
        result = 31 * result + (getDayTime() != null ? getDayTime().hashCode() : 0);
        return result;
    }
}
