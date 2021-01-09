package com.dithub.weather.controller;

import com.dithub.weather.APIHandler;
import com.dithub.weather.WeatherDay;
import com.dithub.weather.WeatherForecast;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import javafx.scene.image.Image;
import java.io.FileNotFoundException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;


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

    @FXML
    public TextField searchBox;

    @FXML
    public Label lblday2;
    @FXML
    public Label lblday3;

    @FXML
    public Label lblday4;
    @FXML
    public Label lblday5;
    @FXML
    public Label lblday6;

    @FXML
    public Label lblday2temp;
    @FXML
    public Label lblday3temp;
    @FXML
    public Label lblday4temp;
    @FXML
    public Label lblday5temp;
    @FXML
    public Label lblday6temp;

    @FXML
    public ImageView imgWeatherImg2;
    @FXML
    public ImageView imgWeatherImg3;
    @FXML
    public ImageView imgWeatherImg4;
    @FXML
    public ImageView imgWeatherImg5;
    @FXML
    public ImageView imgWeatherImg6;

    ClassLoader classLoader = getClass().getClassLoader();

    Image img = new Image(getClass().getResource("/com/dithub/weather/img/icons/02d.png").toString());

    //adding pulled data to UI
    @FXML
    private void initialize() throws FileNotFoundException {

        APIHandler data = new APIHandler();

        //Get Search Request

        searchBox.setOnAction(new EventHandler<ActionEvent>() {
            String tfData = "";
            @Override
            public void handle(ActionEvent event) {
                tfData = searchBox.getText();
                ArrayList<Object> oneDayWeatherDataRequest = data.getCurrentApiData(tfData);
                WeatherDay oneDayWeatherRequest = new WeatherDay(oneDayWeatherDataRequest);
                ArrayList<Object> dailyForecastDataRequest = data.getForecastApiData(tfData);
                WeatherForecast dailyForecastRequest = new WeatherForecast(dailyForecastDataRequest,5);

                ArrayList dayList = getDaysOfWeek();

                String city = oneDayWeatherRequest.cityOneDay;
                String country = oneDayWeatherRequest.countryOneDay;
                String tempCel = oneDayWeatherRequest.tempOneDay;
                String iconIdent = oneDayWeatherRequest.iconOneDay;
                int timezoneOneDay = oneDayWeatherRequest.timezoneOffsetOneDay;
                int timezoneForecast = dailyForecastRequest.timezoneOffsetForecast;
                String imgPath = "/com/dithub/weather/img/icons/";
                Image img = new Image(getClass().getResource(imgPath).toString());

                //setting infos from API to UI lables/img
                lblCity.setText(city+ ", " + country);
                lblCurrTime.setText(getTime());
                lblTemp.setText(tempCel);
                lblCurrDay.setText(getDay());
                imgWeatherImg.setImage(new Image(imgPath+iconIdent+".png"));

                //day 2
                lblday2.setText(String.valueOf(dayList.get(0)));
                imgWeatherImg2.setImage(new Image(imgPath+dailyForecastRequest.iconForecast[0]+".png"));
                lblday2temp.setText(dailyForecastRequest.tempForecast[0]);

                //day 3
                lblday3.setText(String.valueOf(dayList.get(1)));
                imgWeatherImg3.setImage(new Image(imgPath+dailyForecastRequest.iconForecast[1]+".png"));
                lblday3temp.setText(dailyForecastRequest.tempForecast[1]);

                //day 4
                lblday4.setText(String.valueOf(dayList.get(2)));
                imgWeatherImg4.setImage(new Image(imgPath+dailyForecastRequest.iconForecast[2]+".png"));
                lblday4temp.setText(dailyForecastRequest.tempForecast[2]);

                //day 5
                lblday5.setText(String.valueOf(dayList.get(3)));
                imgWeatherImg5.setImage(new Image(imgPath+dailyForecastRequest.iconForecast[3]+".png"));
                lblday5temp.setText(dailyForecastRequest.tempForecast[3]);

                //day 6
                lblday6.setText(String.valueOf(dayList.get(4)));
                imgWeatherImg6.setImage(new Image(imgPath+dailyForecastRequest.iconForecast[4]+".png"));
                lblday6temp.setText(dailyForecastRequest.tempForecast[4]);

                getDaysOfWeek();
            }
        });


        //pulling data from weather data class

        String location = data.getCurrentLocation();
        ArrayList<Object> oneDayWeatherData = data.getCurrentApiData(location);
        WeatherDay oneDayWeather = new WeatherDay(oneDayWeatherData);

        ArrayList<Object> dailyForecastData = data.getForecastApiData(data.getCurrentLocation());

        WeatherForecast dailyForecast = new WeatherForecast(dailyForecastData,5);

        ArrayList dayList = getDaysOfWeek();

        String city = oneDayWeather.cityOneDay;
        String country = oneDayWeather.countryOneDay;
        String tempCel = oneDayWeather.tempOneDay;
        String iconIdent = oneDayWeather.iconOneDay;
        int timezoneOneDay = oneDayWeather.timezoneOffsetOneDay;
        int timezoneForecast = dailyForecast.timezoneOffsetForecast;
        System.out.println(timezoneForecast);
        System.out.println(timezoneOneDay);

        long epoch = System.currentTimeMillis()/1000;
        long correctTimezone = (epoch+timezoneOneDay);
        System.out.println(epoch);

        String imgPath = "/com/dithub/weather/img/icons/";
        Image img = new Image(getClass().getResource(imgPath).toString());

        //setting infos from API to UI lables/img
        lblCity.setText(city+ ", " + country);
        lblCurrTime.setText(getTime());
        lblTemp.setText(tempCel);
        lblCurrDay.setText(getDay());
        imgWeatherImg.setImage(new Image(imgPath+iconIdent+".png"));

        //day 2
        lblday2.setText(String.valueOf(dayList.get(0)));
        imgWeatherImg2.setImage(new Image(imgPath+dailyForecast.iconForecast[0]+".png"));
        lblday2temp.setText(dailyForecast.tempForecast[0]);

        //day 3
        lblday3.setText(String.valueOf(dayList.get(1)));
        imgWeatherImg3.setImage(new Image(imgPath+dailyForecast.iconForecast[1]+".png"));
        lblday3temp.setText(dailyForecast.tempForecast[1]);

        //day 4
        lblday4.setText(String.valueOf(dayList.get(2)));
        imgWeatherImg4.setImage(new Image(imgPath+dailyForecast.iconForecast[2]+".png"));
        lblday4temp.setText(dailyForecast.tempForecast[2]);

        //day 5
        lblday5.setText(String.valueOf(dayList.get(3)));
        imgWeatherImg5.setImage(new Image(imgPath+dailyForecast.iconForecast[3]+".png"));
        lblday5temp.setText(dailyForecast.tempForecast[3]);

        //day 6
        lblday6.setText(String.valueOf(dayList.get(4)));
        imgWeatherImg6.setImage(new Image(imgPath+dailyForecast.iconForecast[4]+".png"));
        lblday6temp.setText(dailyForecast.tempForecast[4]);

        getDaysOfWeek();

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
        DateFormat format = new SimpleDateFormat("EEE d, MMM, YYYY");
        return format.format(currentDay);
    }

    //retrieving 5 upcoming days
    private ArrayList getDaysOfWeek(){
        ArrayList dayList = new ArrayList();
        LocalDate today = LocalDate.now();
        DateTimeFormatter dayFormat = DateTimeFormatter.ofPattern("EEE d");
        for (int i = 1; i <= 5; i++){
            dayList.add(dayFormat.format(today.plusDays(i)));
        }
        return dayList;
    }

}
