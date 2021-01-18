package com.dithub.weather;

import org.json.JSONArray;

import java.util.ArrayList;

public class WeatherForecastHourly {

    public String[] tempForecast = new String[48];
    public String[] iconForecast = new String[48];

    public WeatherForecastHourly(ArrayList<Object> forecastHourly, int hours){
        JSONArray forecastDataHourly = new JSONArray(forecastHourly);
        System.out.println(forecastDataHourly);
        int count = 0;

        for(int i = 0; i<hours; i++){
           // this.tempForecast[count] = forecastDataHourly.getJSONObject(0).getJSONArray("hourly").getJSONObject(i).getJSONArray("weather").getJSONObject(0).get("icon").toString();
            count++;
        }
        count = 0;

        for(int i = 0; i<hours; i++){
            //this.iconForecast[count] = forecastDataHourly.getJSONObject(0).getJSONArray("hourly").getJSONObject(i).getJSONArray("weather").getJSONObject(0).get("icon").toString();
            count++;
        }
    }
}