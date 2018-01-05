package com.example.marin.ms_pb_17;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.Window;
import android.widget.FrameLayout;
import android.view.View;
import android.widget.RelativeLayout;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.content.Context;

public class IntroApp extends Activity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_intro_app);

        FrameLayout fullLayoutButton = (FrameLayout) findViewById(R.id.layoutCurrency);
        RelativeLayout fullLayoutButtonMap = (RelativeLayout) findViewById(R.id.layoutMap);

        fullLayoutButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
              Intent intent = new Intent(IntroApp.this, MainActivity.class);
               startActivity(intent);
            }
        });
        fullLayoutButtonMap.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(IntroApp.this, MapsActivity.class);
                startActivity(intent);
            }
        });

        //declaring Sensor Manager and sensor type
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    public void onAccuracyChanged(Sensor arg0, int arg1) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        if (Math.abs(x) > Math.abs(y)) {
            if (x < 0) { //right
                Intent intent = new Intent(IntroApp.this, MapsActivity.class);
                startActivity(intent);
            }
            if (x > 0) { //left
                Intent intent = new Intent(IntroApp.this, MainActivity.class);
                startActivity(intent);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //unregister Sensor listener
        sensorManager.unregisterListener(this);
    }
}