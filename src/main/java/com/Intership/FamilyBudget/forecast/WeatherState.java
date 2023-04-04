package com.Intership.FamilyBudget.forecast;

import com.Intership.FamilyBudget.forecast.enums.WeatherCondition;
import lombok.Data;

@Data
public class WeatherState {
    private final int id;
    private final String name;
    private final String description;
    private String iconId;
    private final WeatherCondition weatherConditionEnum;

    public WeatherState(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.weatherConditionEnum = WeatherCondition.getById(id);
    }
    public String getWeatherIconUrl() {
        if (iconId != null) {
            return WeatherCondition.getIconUrl(iconId);
        }
        if (weatherConditionEnum != null) {
            // return the default one for the current weather condition
            return weatherConditionEnum.getDayIconUrl();
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WeatherState)) return false;

        WeatherState that = (WeatherState) o;

        if (getId() != that.getId()) return false;
        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        if (getDescription() != null ? !getDescription().equals(that.getDescription()) : that.getDescription() != null)
            return false;
        if (getIconId() != null ? !getIconId().equals(that.getIconId()) : that.getIconId() != null) return false;
        return getWeatherConditionEnum() == that.getWeatherConditionEnum();
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getIconId() != null ? getIconId().hashCode() : 0);
        result = 31 * result + (getWeatherConditionEnum() != null ? getWeatherConditionEnum().hashCode() : 0);
        return result;
    }
}
