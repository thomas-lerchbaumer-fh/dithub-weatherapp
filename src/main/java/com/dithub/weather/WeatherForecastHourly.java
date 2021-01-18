package com.dithub.weather;

import org.json.JSONArray;

import java.util.ArrayList;

public class WeatherForecastHourly {

    public String[] tempForecastHourly = new String[48];
    public String[] iconForecastHourly = new String[48];

    public WeatherForecastHourly(ArrayList<Object> forecastHourly, int hours){
        JSONArray forecastDataHourly = new JSONArray(forecastHourly);
        System.out.println(forecastDataHourly);
        int count = 0;

        for(int i = 0; i<hours; i++){
            this.tempForecastHourly[count] = forecastDataHourly.getJSONObject(0).getJSONArray("hourly").getJSONObject(i).getInt("temp") + "\u00B0";
            count++;
        }
        count = 0;

        for(int i = 0; i<hours; i++){
            this.iconForecastHourly[count] = forecastDataHourly.getJSONObject(0).getJSONArray("hourly").getJSONObject(i).getJSONArray("weather").getJSONObject(0).get("icon").toString();
            count++;
        }
    }
}