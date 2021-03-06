package com.dithub.weather;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;
import org.json.*;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;



public class APIHandler {

    //user search request here
    //Please implement logic for search request (pulling data) you can use dummy city, search field input will be done by me
    public static ArrayList<Object> getHourlyForecastTwoDaysApiData(String lat, String lon) {
        ArrayList<Object> listdata = new ArrayList<Object>();
        String appId = "8bcb04fb242f29f824ec1efb44188c91";

        try {
            URL url = new URL("http://api.openweathermap.org/data/2.5/onecall"
                    + "?lat=" + lat
                    + "&lon=" + lon
                    + "&units=metric"
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


    public static ArrayList<Object> getCurrentApiData(String location) {
        ArrayList<Object> listdata = new ArrayList<Object>();
        String appId = "8bcb04fb242f29f824ec1efb44188c91";

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

    public static String getCurrentLocation() {
        String cityName = "";


        try {
            InetAddress myIP=InetAddress.getLocalHost();

            URL url = new URL("http://www.geoplugin.net/xml.gp?"
                    +"ip" +myIP);;

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));

                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(conn.getInputStream());
                cityName = doc.getElementsByTagName("geoplugin_city").item(0).getTextContent();
            } else {
                System.err.println("Wrong response code: " + responseCode);
            }
        } catch (Exception ex) {
            System.err.println("Request error: " + ex.getMessage());
        }
        return cityName;
    }
}


