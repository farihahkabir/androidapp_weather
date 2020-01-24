package com.example.weather;


import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;


public class WeatherLondon extends AsyncTask<String, Void, String> {
    String resultl;
    Float templC;
    DecimalFormat templCFormat = new DecimalFormat("#.00");

    @Override
    protected String doInBackground(String... urls) {
        resultl = "";
        URL link;
        HttpURLConnection myConnection = null;

        try{
            link = new URL(urls[0]);
            myConnection = (HttpURLConnection)link.openConnection();
            InputStream in = myConnection.getInputStream();
            InputStreamReader myStreamReader = new InputStreamReader(in);
            int data = myStreamReader.read();
            while (data!=-1){
                char current = (char)data;
                resultl+= current;
                data = myStreamReader.read();
            }
            return resultl;
        } catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s){
        super.onPostExecute(s);

        try{
            JSONObject myObjectl = new JSONObject(resultl);
            JSONArray weather = myObjectl.getJSONArray("weather");
            JSONObject zeroInd = weather.getJSONObject(0);
            JSONObject main = new JSONObject(myObjectl.getString("main"));
            JSONObject wind = new JSONObject(myObjectl.getString("wind"));
            JSONObject coord = new JSONObject(myObjectl.getString("coord"));

            String placeNamelon = myObjectl.getString("name");
            String desclon = zeroInd.getString("description");
            String temperaturelon = main.getString("temp");
            String humlon = main.getString("humidity");
            String windspeedlon = wind.getString("speed");
            String longitudelon = coord.getString("lon");
            String latitudelon = coord.getString("lat");

            templC = Float.parseFloat(temperaturelon);
            templC = templC - 273;

            LondonActivity.placel.setText(placeNamelon);
            LondonActivity.descriptionl.setText(desclon);
            LondonActivity.templ.setText(templCFormat.format(templC) + "" + "°C");
            LondonActivity.humidityl.setText(humlon + "%");
            LondonActivity.speedl.setText(windspeedlon + "km/hr");
            LondonActivity.lonl.setText(longitudelon + "° E");
            LondonActivity.latl.setText(latitudelon + "° N");


        } catch(JSONException e){
            e.printStackTrace();
        }
    }
}

