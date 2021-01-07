package com.dithub.weather.controller;

import com.dithub.weather.WeatherData;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;


import javafx.scene.image.Image;
import org.json.JSONArray;

import java.io.FileNotFoundException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;


import java.util.ArrayList;
import java.util.Date;

public class MainUiController {

    @FXML
    private Label lblCity;

    @FXML
    private Label lblCurrTime;

    @FXML
    private Label lblTemp;

    @FXML
    private Label lblCurrDay;

    @FXML
    public ImageView imgWeatherImg;
    ClassLoader classLoader = getClass().getClassLoader();

    Image img = new Image(getClass().getResource("/com/dithub/weather/img/icons/02d.png").toString());

    //adding pulled data to UI
    @FXML
    private void initialize() throws FileNotFoundException {

        //pulling data from weather data class
        WeatherData newData = new WeatherData();
        ArrayList<Object> listWeather = newData.getCurrentApiData("Vienna");
        System.out.println(listWeather);
        JSONArray firstDay = new JSONArray(listWeather);


        String city = firstDay.getJSONObject(0).get("name").toString();
        String country = firstDay.getJSONObject(0).getJSONObject("sys").get("country").toString();
        String tempCel = firstDay.getJSONObject(0).getJSONObject("main").get("temp").toString();
       // String iconIdent = firstDay.getJSONObject(0).getJSONArray("weather").getJSONObject(0).get("icon").toString();
        String iconIdent = "02d";
        System.out.println(iconIdent);

        String imgPath = "/com/dithub/weather/img/icons/"+iconIdent+".png";
        Image img = new Image(getClass().getResource(imgPath).toString());


        System.out.println(city);

        lblCity.setText(city+ ", " + country);
        lblCurrTime.setText(getTime());
        lblTemp.setText(tempCel);
        lblCurrDay.setText(getDay());
        imgWeatherImg.setImage(img);
    }

    //formatting current time (on app launch)
    private String getTime(){
      Date currentTime = new Date();
      DateFormat format = new SimpleDateFormat("HH:mm");

      return format.format(currentTime);

    }

    //formatting current day (on app launch)
    private String getDay(){
        Date currentDay = new Date();
        DateFormat format = new SimpleDateFormat("EEE d, YYYY");

        return format.format(currentDay);
    }

    private WeatherData getDataWeather(){
        WeatherData dataNew = new WeatherData();
        dataNew.getCurrentApiData("Vienna");

        return dataNew;

    }



}
