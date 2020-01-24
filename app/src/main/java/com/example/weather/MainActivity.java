package com.example.weather;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    static TextView place;
    static TextView description;
    static TextView lon;
    static TextView lat;
    static TextView temp;
    static TextView humidity;
    static TextView speed;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        place = (TextView)findViewById(R.id.place);
        description = (TextView)findViewById(R.id.description);
        lon = (TextView)findViewById(R.id.lon);
        lat = (TextView)findViewById(R.id.lat);
        temp = (TextView)findViewById(R.id.temp);
        humidity = (TextView)findViewById(R.id.humidity);
        speed = (TextView)findViewById(R.id.speed);

        Weather getData = new Weather();
        getData.execute("http://api.openweathermap.org/data/2.5/" + "weather?q=Dhaka&appid=b62a4e457f16ef6d9af78b7a7e093e72");

    }

    public void onClickNext(View view) {
        Intent intent = new Intent(this, LondonActivity.class);
        startActivity(intent);
    }

    public void onClickPrev(View view) {
        Intent intent = new Intent(this, MountViewActivity.class);
        startActivity(intent);
    }
}
