package com.dithub.weather.controller;


import com.dithub.weather.APIHandler;
import com.dithub.weather.WeatherDay;
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

import static java.lang.Math.round;

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
        APIHandler data = new APIHandler();
        ArrayList<Object> oneDayWeatherData = data.getCurrentApiData("Vienna");
        WeatherDay oneDayWeather = new WeatherDay(oneDayWeatherData);

<<<<<<< Updated upstream
        // --------------- This block will be removed once the linker is ready ------------------------
        String city = firstDay.getJSONObject(0).get("name").toString();
        String country = firstDay.getJSONObject(0).getJSONObject("sys").get("country").toString();
        int tempCel = firstDay.getJSONObject(0).getJSONObject("main").getInt("temp");
        String temp = tempCel + "\u00B0";
        String iconIdent = firstDay.getJSONObject(0).getJSONArray("weather").getJSONObject(0).get("icon").toString();
=======

        String city = oneDayWeather.cityOneDay;
        String country = oneDayWeather.countryOneDay;
        String tempCel = oneDayWeather.tempOneDay;
        //String iconIdent = oneDayWeather.iconOneDay;
        String iconIdent = "02d";
>>>>>>> Stashed changes
        System.out.println(iconIdent);
        String imgPath = "/com/dithub/weather/img/icons/"+iconIdent+".png";
        Image img = new Image(getClass().getResource(imgPath).toString());
        // --------------- This block will be removed once the linker is ready ------------------------

        //setting infos from API to UI lables/img
        lblCity.setText(city+ ", " + country);
        lblCurrTime.setText(getTime());
        lblTemp.setText(temp);
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
<<<<<<< Updated upstream


=======
>>>>>>> Stashed changes
}
