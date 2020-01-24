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


public class Weather extends AsyncTask<String, Void, String> {
    String result;
    Float tempC;
    DecimalFormat tempCFormat = new DecimalFormat("#.00");

    @Override
    protected String doInBackground(String... urls) {
        result = "";
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
                result+= current;
                data = myStreamReader.read();
            }
            return result;
        } catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s){
        super.onPostExecute(s);

        try{
            JSONObject myObject = new JSONObject(result);
            JSONArray weather = myObject.getJSONArray("weather");
            JSONObject zeroInd = weather.getJSONObject(0);
            JSONObject main = new JSONObject(myObject.getString("main"));
            JSONObject wind = new JSONObject(myObject.getString("wind"));
            JSONObject coord = new JSONObject(myObject.getString("coord"));

            String placeName = myObject.getString("name");
            String desc = zeroInd.getString("description");
            String temperature = main.getString("temp");
            String hum = main.getString("humidity");
            String windspeed = wind.getString("speed");
            String longitude = coord.getString("lon");
            String latitude = coord.getString("lat");

            tempC = Float.parseFloat(temperature);
            tempC = tempC - 273;

            MainActivity.place.setText(placeName);
            MainActivity.description.setText(desc);
            MainActivity.temp.setText(tempCFormat.format(tempC)+ "" + "°C");
            MainActivity.humidity.setText(hum + "%");
            MainActivity.speed.setText(windspeed + "km/hr");
            MainActivity.lon.setText(longitude + "° E");
            MainActivity.lat.setText(latitude + "° N");


        } catch(JSONException e){
            e.printStackTrace();
        }
    }
}
