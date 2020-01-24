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


public class WeatherMountView extends AsyncTask<String, Void, String> {
    String resultmv;
    Float tempC;
    DecimalFormat tempCFormat = new DecimalFormat("#.00");

    @Override
    protected String doInBackground(String... urls) {
        resultmv = "";
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
                resultmv+= current;
                data = myStreamReader.read();
            }
            return resultmv;
        } catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s){
        super.onPostExecute(s);

        try{
            JSONObject myObjectmv = new JSONObject(resultmv);
            JSONArray weather = myObjectmv.getJSONArray("weather");
            JSONObject zeroInd = weather.getJSONObject(0);
            JSONObject main = new JSONObject(myObjectmv.getString("main"));
            JSONObject wind = new JSONObject(myObjectmv.getString("wind"));
            JSONObject coord = new JSONObject(myObjectmv.getString("coord"));

            String placeNamemtv = myObjectmv.getString("name");
            String descmtv = zeroInd.getString("description");
            String temperaturemtv = main.getString("temp");
            String hummtv = main.getString("humidity");
            String windspeedmtv = wind.getString("speed");
            String longitudemtv = coord.getString("lon");
            String latitudemtv = coord.getString("lat");

            tempC = Float.parseFloat(temperaturemtv);
            tempC = tempC - 273;

            MountViewActivity.placemv.setText(placeNamemtv);
            MountViewActivity.descriptionmv.setText(descmtv);
            MountViewActivity.tempmv.setText(tempCFormat.format(tempC)+ "" + "°C");
            MountViewActivity.humiditymv.setText(hummtv + "%");
            MountViewActivity.speedmv.setText(windspeedmtv + "km/hr");
            MountViewActivity.lonmv.setText(longitudemtv + "° E");
            MountViewActivity.latmv.setText(latitudemtv + "° N");


        } catch(JSONException e){
            e.printStackTrace();
        }
    }
}
