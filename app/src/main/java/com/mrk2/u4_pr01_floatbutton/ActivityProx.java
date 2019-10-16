package com.mrk2.u4_pr01_floatbutton;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.provider.ContactsContract;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class ActivityProx extends AppCompatActivity implements SensorEventListener  {

    TextView cad;
    Sensor sensor;
    SensorManager sm;
    Sensor proximitySensor;
    Vibrator v;
    @Override
    protected void onResume() {
        super.onResume();
        if(proximitySensor != null){
            sm.registerListener(this,proximitySensor,SensorManager.SENSOR_DELAY_GAME);
        }else{
            Toast.makeText(this, "No tienes sensor", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        sm.unregisterListener(this,sensor);
    }

    @Override
    protected void onStop() {
        super.onStop();
        SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        sm.unregisterListener(this,sensor);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prox);
        cad = findViewById(R.id.idSens);
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        proximitySensor = sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.values[0] < proximitySensor.getMaximumRange()){
            v.vibrate(100);
            cad.setText("NO VEOOOOOOOOOOO");
            getSupportActionBar().hide();
            getWindow().getDecorView().setBackgroundColor(Color.GREEN);
        }else{
            v.vibrate(100);
            getSupportActionBar().show();
            cad.setText("YA PUEDO LUCHAR");
            getWindow().getDecorView().setBackgroundColor(Color.RED);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        cad.setText("YA VEOOOOOOOOOOOO");

    }
}
