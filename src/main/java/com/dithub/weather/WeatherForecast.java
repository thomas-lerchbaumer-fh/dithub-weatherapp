package com.dithub.weather;

import org.json.JSONArray;

import java.util.ArrayList;

public class WeatherForecast {

    public String countryForecast;
    public String cityForecast;
    public int timezoneOffsetForecast;
    public String[] tempForecast = new String[5];
    public String[] iconForecast = new String[5];

    public WeatherForecast(ArrayList<Object> forecast, int noOfDays){
        JSONArray forecastData = new JSONArray(forecast);
        int count = 0;

        this.countryForecast = forecastData.getJSONObject(0).getJSONObject("city").get("country").toString();
        this.cityForecast = forecastData.getJSONObject(0).getJSONObject("city").get("name").toString();

        this.timezoneOffsetForecast = (Integer)forecastData.getJSONObject(0).getJSONObject("city").get("timezone");
        for(int i = 0; i<noOfDays*8; i=i+8){
            this.tempForecast[count] = forecastData.getJSONObject(0).getJSONArray("list").getJSONObject(i).getJSONObject("main").getInt("temp") + "\u00B0";
            count++;
        }
        count = 0;

        for(int i = 0; i<noOfDays*8; i=i+8){
            this.iconForecast[count] = forecastData.getJSONObject(0).getJSONArray("list").getJSONObject(i).getJSONArray("weather").getJSONObject(0).get("icon").toString();
            count++;
        }
    }
}
