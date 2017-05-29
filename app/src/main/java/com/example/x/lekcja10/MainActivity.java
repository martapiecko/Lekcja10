package com.example.x.lekcja10;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    protected SensorManager sensorManager;
    protected Kompas kompas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialization();
        pokazSensors();
        kompas.registerListeners();
    }

    public void initialization(){
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        kompas = new Kompas(this, sensorManager);
        kompas.strzalkaKompasu = (ImageView) findViewById(R.id.kompas);
        kompas.azymutTextView = (TextView) findViewById(R.id.azymut);
    }

    public void pokazSensors(){
        TextView sensorsTextView = (TextView) findViewById(R.id.sensors);
        List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);

        String wszystkieSensors = new String();
            for(Sensor sensor : sensorList){
                wszystkieSensors += sensor.getName() + "\n";
            }
        sensorsTextView.setText(wszystkieSensors);
    }
}