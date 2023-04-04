package com.Intership.FamilyBudget.forecast;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
    private RestTemplate restTemplate;
    private String apiKey = "7d18409cbd2628e32e6b80eb8ae62f5a";

    @Autowired
    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Weather getWeather(String zipCode) {
        String url = "https://api.openweathermap.org/data/2.5/weather?zip=" + zipCode + "&appid=" + apiKey;
        WeatherResponse response = restTemplate.getForObject(url, WeatherResponse.class);
        if (response != null) {
            return new Weather(response.getTemperature().getMinTemperature(), response.getTemperature().getMaxTemperature(),
                    response.getHumidity().getValue(), response.getRain().getThreeHourLevel()
                    ,response.getSnow().getThreeHourLevel(), response.getClouds().getValue());
        }else throw new NullPointerException("No response from server");
    }
}
