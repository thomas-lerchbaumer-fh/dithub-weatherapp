package com.dithub.weather;

import java.util.ArrayList;
import org.json.*;



public class WeatherDay {

    public String countryOneDay;
    public String cityOneDay;
    public String tempOneDay;
    public String iconOneDay;
    public int timezoneOffsetOneDay;


    public WeatherDay(ArrayList<Object> firstday){
        JSONArray oneDayData = new JSONArray(firstday);

        this.countryOneDay = oneDayData.getJSONObject(0).getJSONObject("sys").get("country").toString();
        this.cityOneDay = oneDayData.getJSONObject(0).get("name").toString();
        this.tempOneDay = oneDayData.getJSONObject(0).getJSONObject("main").getInt("temp") +"\u00B0";
        this.iconOneDay = oneDayData.getJSONObject(0).getJSONArray("weather").getJSONObject(0).get("icon").toString();
        this.timezoneOffsetOneDay = (Integer)oneDayData.getJSONObject(0).get("timezone");
    }
}
