package com.mrk2.u4_pr01_floatbutton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.opengl.ETC1;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

public class ActivityMap extends AppCompatActivity implements LocationListener {

    ImageButton btnFind, btnSearch;
    EditText etLat,etLong;
    ProgressBar pb;
    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        controls();
        events();
    }

    private void events() {
        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMyCordinates();
            }
        });
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intentMap = new Intent(ActivityMap.this,MapsActivity.class);
                    startActivity(intentMap);
                }catch (Exception e){
                    Toast.makeText(ActivityMap.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    private void getMyCordinates() {
        if(getGPStatus()){
            pb.setVisibility(View.VISIBLE);
            long time = 500; //Lecture Time
            float minDist = 10; //Distance of Lecture
            //check permissions
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
                return;
            }
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,time,minDist,(LocationListener)this);

        }else{
            pb.setVisibility(View.INVISIBLE);
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("GPS Desactivated");
            alert.setCancelable(false);
            alert.setPositiveButton("Activate GPS", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(intent);
                }
            });
            alert.setNegativeButton("Cancel",null);
            alert.create().show();
        }
    }

    private boolean getGPStatus() {
        ContentResolver content = getBaseContext().getContentResolver();
        boolean status = Settings.Secure.isLocationProviderEnabled(content, LocationManager.GPS_PROVIDER);
        return status;
    }

    private void controls() {
        btnFind = findViewById(R.id.btnFind);
        btnSearch = findViewById(R.id.btnSearch);
        etLat = findViewById(R.id.am_et_lat);
        etLong = findViewById(R.id.am_et_long);
        pb = findViewById(R.id.progressBar);
        pb.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onLocationChanged(Location location) {
    etLong.setText("");
    etLat.setText("");
        pb.setVisibility(View.INVISIBLE);
        etLong.setText(String.valueOf(location.getLongitude()));
        etLat.setText(String.valueOf(location.getLatitude()));
    }


    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
