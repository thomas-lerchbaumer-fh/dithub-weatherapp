package com.dithub.weather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import org.json.*;


public class WeatherData {

    //user search request here
    //Please implement logic for search request (pulling data) you can use dummy city, search field input will be done by me

    public static ArrayList<Object> getCurrentApiData(String location) {
        ArrayList<Object> listdata = new ArrayList<Object>();
        String appId = "b1f2d01d253273e36e3005b89b2e84db";

        try {
            URL url = new URL("http://api.openweathermap.org/data/2.5/weather"
                    + "?units=metric"
                    + "&q=" + location
                    + "&appid=" + appId);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = in.readLine()) != null) {
                    sb.append(line);
                }
                JSONObject json = new JSONObject(sb.toString());
                listdata.add(json);
                in.close();
            } else {
                System.err.println("Wrong response code: " + responseCode);
            }
        } catch (Exception ex) {
            System.err.println("Request error: " + ex.getMessage());
        }
        return listdata;
    }


    public static ArrayList<Object> getForecastApiData(String location) {
        ArrayList<Object> listdata = new ArrayList<Object>();
        String appId = "b1f2d01d253273e36e3005b89b2e84db";

        try {
            URL url = new URL("http://api.openweathermap.org/data/2.5/forecast"
                    + "?units=metric"
                    + "&q=" + location
                    + "&appid=" + appId);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = in.readLine()) != null) {
                    sb.append(line);
                }
                JSONObject json = new JSONObject(sb.toString());
                listdata.add(json);
                in.close();
            } else {
                System.err.println("Wrong response code: " + responseCode);
            }
        } catch (Exception ex) {
            System.err.println("Request error: " + ex.getMessage());
        }
        return listdata;
    }
}


