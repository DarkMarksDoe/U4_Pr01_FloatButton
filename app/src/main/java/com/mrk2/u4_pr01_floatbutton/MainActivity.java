package com.mrk2.u4_pr01_floatbutton;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //En este evento se programa la accion para el FloatingButton
                Snackbar.make(view, "Nocierto, no le piques", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        try {
            if (id == R.id.me_audio) {
                Intent intentAudio = new Intent(MainActivity.this, media_player.class);
                startActivity(intentAudio);
            }else if(id == R.id.me_video){
                Intent intentVid = new Intent(MainActivity.this, video_player.class);
                startActivity(intentVid);
            }else if(id == R.id.me_videoView){
                Intent intentVid = new Intent(MainActivity.this, ActivityVideoView.class);
                startActivity(intentVid);
            }else if(id == R.id.me_img_camera) {
                Intent intentVid = new Intent(MainActivity.this, ActivityCamera.class);
                startActivity(intentVid);
            }else if(id == R.id.me_Touch) {
                Intent intentVid = new Intent(MainActivity.this, ActivityTapSensor.class);
                startActivity(intentVid);
            }else if(id == R.id.me_Prox) {
                Intent intentVid = new Intent(MainActivity.this, ActivityProx.class);
                startActivity(intentVid);
            }else if(id == R.id.me_map1) {
                Intent intentVid = new Intent(MainActivity.this, ActivityMap.class);
                startActivity(intentVid);
            }


            }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }


        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }
}
