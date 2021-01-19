package com.dithub.weather.controller;

import com.dithub.weather.APIHandler;
import com.dithub.weather.WeatherDay;
import com.dithub.weather.WeatherForecast;
import com.dithub.weather.WeatherForecastHourly;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;



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

    @FXML
    public ListView hourlyForecast;

    //adding pulled data to UI
    @FXML
    private void initialize() throws FileNotFoundException {

        APIHandler data = new APIHandler();

        //Get Search Request
        searchBox.setOnAction(new EventHandler<ActionEvent>() {
            String tfData = "";
            @Override
            public void handle(ActionEvent event) {
                tfData =  replaceSpecialSignInput(searchBox.getText());
                try{
                    setSceneProperties(tfData);
                   }
                catch (Exception ex0){
                    searchRequestNotFound(tfData);
                }
            }
        });
        hourlyForecast.getItems().clear();
     setSceneProperties("");

    }
    //formatting current time (on app launch)
    private String getTime(int offset){
        ZonedDateTime utc = ZonedDateTime.now(ZoneOffset.UTC);
        utc = utc.plusSeconds(offset);

        return utc.format(DateTimeFormatter.ofPattern("HH:mm"));
    }
    //formatting current day (on app launch)
    private String getDay(){
        Date currentDay = new Date();
        DateFormat format = new SimpleDateFormat("EEE d, MMM, YYYY",new Locale("en"));
        return format.format(currentDay);
    }

    //retrieving 5 upcoming days
    private ArrayList getDaysOfWeek(){
        ArrayList dayList = new ArrayList();
        LocalDate today = LocalDate.now();
        DateTimeFormatter dayFormat = DateTimeFormatter.ofPattern("EEE d",new Locale("en"));
        for (int i = 1; i <= 5; i++){
            dayList.add(dayFormat.format(today.plusDays(i)));
        }
        return dayList;
    }

    private void searchRequestNotFound(String city){
        //creating alert window when api did not return any search request
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Not Found");
        alert.setHeaderText("Sorry, we were not able to find: " + city + ", please try again.");
        alert.setContentText(replaceSpecialSignOutput("Please be aware that shortcodes etc. cannot be found e.g. 'St. Pölten' won't work but 'Sankt Pölten' would work"));
        //adding stylesheet to alert window
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(
                getClass().getResource("/com/dithub/weather/css/style.css/").toExternalForm());
        dialogPane.getStyleClass().add("notFound");
        alert.showAndWait();
    }

    private String replaceSpecialSignOutput(String input){
        //replaces all "Umlaute" with unicode
        String output = input.replace("ü", "\u00fc")
                .replace("ö", "\u00f6")
                .replace("ä", "\u00e4")
                .replace("ß", "\u00df");
        return output;

    }

    private String replaceSpecialSignInput(String input){
        // in case of user searches for e.g. Sankt Pölten  --> ö will be replaced with oe so search request is sucessfull
        String output = input.replace("\u00fc", "ue")
                .replace("\u00f6", "oe")
                .replace("\u00e4", "ae")
                .replace("\u00df", "ss")
                .replaceAll("\u00dc(?=[a-z\u00e4\u00f6\u00fc\u00df ])", "Ue")
                .replaceAll("\u00d6(?=[a-z\u00e4\u00f6\u00fc\u00df ])", "Oe")
                .replaceAll("\u00c4(?=[a-z\u00e4\u00f6\u00fc\u00df ])", "Ae")
                .replace("\u00dc", "UE")
                .replace("\u00d6", "OE")
                .replace("\u00c4", "AE");
        return output;

    }


    private void setSceneProperties(String searchRequest){
        APIHandler data = new APIHandler();

        //pulling data from weather data class

        ArrayList<Object> oneDayWeatherData;
        WeatherDay oneDayWeather;
        ArrayList<Object> dailyForecastData;
        WeatherForecast dailyForecast;
        ArrayList<Object> twoDaysForecastDataHourly;
        WeatherForecastHourly twoDaysHourly;

        if(searchRequest == ""){
            String location = data.getCurrentLocation();
            oneDayWeatherData = data.getCurrentApiData(location);
            oneDayWeather = new WeatherDay(oneDayWeatherData);
            dailyForecastData = data.getForecastApiData(data.getCurrentLocation());
            dailyForecast = new WeatherForecast(dailyForecastData,5);
            twoDaysForecastDataHourly = data.getHourlyForecastTwoDaysApiData(oneDayWeather.lat, oneDayWeather.lon);
            twoDaysHourly = new WeatherForecastHourly(twoDaysForecastDataHourly, 48);
        }else {
            oneDayWeatherData = data.getCurrentApiData(searchRequest);
            oneDayWeather = new WeatherDay(oneDayWeatherData);
            dailyForecastData = data.getForecastApiData(searchRequest);
            dailyForecast = new WeatherForecast(dailyForecastData,5);

            twoDaysForecastDataHourly = data.getHourlyForecastTwoDaysApiData(oneDayWeather.lat, oneDayWeather.lon);
            twoDaysHourly = new WeatherForecastHourly(twoDaysForecastDataHourly, 48);
            //clearing list view (hourly forecast) otherwise updates won't be shown
            hourlyForecast.getItems().clear();
        }


        ArrayList dayList = getDaysOfWeek();

        String city = oneDayWeather.cityOneDay;
        String country = oneDayWeather.countryOneDay;
        String tempCel = oneDayWeather.tempOneDay;
        String iconIdent = oneDayWeather.iconOneDay;

        int timezoneOneDay = oneDayWeather.timezoneOffsetOneDay;

        String imgPath = "/com/dithub/weather/img/icons/";

        //setting infos from API to UI lables/img
        lblCity.setText(replaceSpecialSignOutput(city)+ ", " + replaceSpecialSignOutput(country));
        lblCurrTime.setText(getTime(timezoneOneDay));
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


        //hourly forecast
        String fetchTime = getTime(timezoneOneDay);
        int startingHour = Integer.parseInt(fetchTime.substring(0,2));

        int hourCounter = startingHour +1;

        // setting orientation for listview
        hourlyForecast.setOrientation(Orientation.HORIZONTAL);

        for(int i = 1; i < 48; i++){
            SplitPane sp = new SplitPane();
            Image img1 = new Image(imgPath+twoDaysHourly.iconForecastHourly[i]+".png");

            //setting icon for hourly forecast
            ImageView imgFin = new ImageView();
            imgFin.setImage(img1);
            imgFin.setFitWidth(50);
            imgFin.setFitHeight(50);

            //adding degree to hourly forecast
            Label text = new Label();
            text.setText(twoDaysHourly.tempForecastHourly[i]);
            text.setStyle("-fx-text-fill: white");
            text.setFont(new Font("System", 14));

            //calculating the time
            int increaseTime = hourCounter;
            if(hourCounter >= 24){
                hourCounter = 0;
            }
            hourCounter++;
            String incTime = increaseTime +":00";
            Label time = new Label(incTime);
            time.setStyle("-fx-text-fill: cc6600");

            //adding all 3 elements to split pane
            sp.setOrientation(Orientation.VERTICAL);
            sp.getItems().add(text);
            sp.getItems().add(imgFin);
            sp.getItems().add(time);

            //adding info to list view
            hourlyForecast.getItems().addAll(sp);

        }

        getDaysOfWeek();

    }

}
