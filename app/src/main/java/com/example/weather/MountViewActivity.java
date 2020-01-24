package com.example.weather;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MountViewActivity extends AppCompatActivity {
    static TextView placemv;
    static TextView descriptionmv;
    static TextView lonmv;
    static TextView latmv;
    static TextView tempmv;
    static TextView humiditymv;
    static TextView speedmv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activiy_mountview);

        placemv = (TextView)findViewById(R.id.placemv);
        descriptionmv = (TextView)findViewById(R.id.descriptionmv);
        lonmv = (TextView)findViewById(R.id.lonmv);
        latmv = (TextView)findViewById(R.id.latmv);
        tempmv = (TextView)findViewById(R.id.tempmv);
        humiditymv = (TextView)findViewById(R.id.humiditymv);
        speedmv = (TextView)findViewById(R.id.speedmv);

        WeatherMountView getDataMV = new WeatherMountView();
        getDataMV.execute("https://samples.openweathermap.org/data/2.5/" + "weather?zip=94040,us&appid=b6907d289e10d714a6e88b30761fae22");

    }

    public void onClickPrev(View view) {
        Intent intent = new Intent(this, LondonActivity.class);
        startActivity(intent);
    }
}
