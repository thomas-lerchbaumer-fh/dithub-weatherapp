package com.dithub.weather.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.awt.*;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


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
        lblCity.setText("Vienna");
        lblCurrTime.setText(getTime());
        lblTemp.setText("23°");
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



}
