package com.example.x.lekcja10;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class Kompas implements SensorEventListener {
    private float azymut = 0f;
    private float aktualnyAzymut = 0;
    private Sensor orientacja;
    private SensorManager sensorManager;

    ImageView strzalkaKompasu;
    TextView azymutTextView;

    public Kompas(Context context, SensorManager sensorManager){
        this.sensorManager = sensorManager;
        orientacja = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
    }

    public void regulacjaStrzalkiKompasu(){
        Animation animacja = new RotateAnimation(-aktualnyAzymut, -azymut, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        aktualnyAzymut = azymut;
        animacja.setRepeatCount(0);
        animacja.setDuration(500);
        animacja.setFillAfter(true);
        strzalkaKompasu.startAnimation(animacja);
    }

    public void aktualizacjaAzymut(){
        azymutTextView.setText(Float.toString(azymut));
    }

    public void registerListeners(){
        sensorManager.registerListener(this, orientacja, SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if( event.sensor.getType() == Sensor.TYPE_ORIENTATION ){
            azymut = (event.values[0] + 360 ) % 360;
            aktualizacjaAzymut();
            regulacjaStrzalkiKompasu();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}
}
