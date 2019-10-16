package com.mrk2.u4_pr01_floatbutton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.TextView;

public class ActivityTapSensor extends AppCompatActivity {
TextView tvATS, tvX,tvY,tvZ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tap_sensor);
        cargarControl();
    }

    private void cargarControl() {
        tvATS = findViewById(R.id.tvATS);
        tvX= findViewById(R.id.tvX);
        tvY= findViewById(R.id.tvY);
        tvZ= findViewById(R.id.tvZ);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                tvATS.setText("Down");
                break;
            case MotionEvent.ACTION_UP:
                tvATS.setText("Up");
                break;
                
        }
        return true;
    }
}
