package com.example.weather;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LondonActivity extends AppCompatActivity {
    static TextView placel;
    static TextView descriptionl;
    static TextView lonl;
    static TextView latl;
    static TextView templ;
    static TextView humidityl;
    static TextView speedl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lon);
        placel = (TextView)findViewById(R.id.placel);
        descriptionl = (TextView)findViewById(R.id.descriptionl);
        lonl = (TextView)findViewById(R.id.lonl);
        latl = (TextView)findViewById(R.id.latl);
        templ = (TextView)findViewById(R.id.templ);
        humidityl = (TextView)findViewById(R.id.humidityl);
        speedl = (TextView)findViewById(R.id.speedl);

        WeatherLondon getLonData = new WeatherLondon();
        getLonData.execute("https://samples.openweathermap.org/data/2.5/" + "weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22");
    }

    public void onClickPrev(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onClickNext(View view) {
        Intent intent = new Intent(this, MountViewActivity.class);
        startActivity(intent);
    }
}
