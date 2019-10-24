package com.mrk2.u4_pr01_floatbutton;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class media_player extends AppCompatActivity {
    ImageButton btnPlay, btnPause, btnStop;
    MediaPlayer medPlay;

    @Override
    protected void onStop() {
        super.onStop();
        medPlay.stop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player);
        try {
            medPlay = MediaPlayer.create(getApplicationContext(),R.raw.a1);
            cargarControles();
            cargarEventos();
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    private void cargarEventos() {
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
            }
        });
        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pause();
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stop();
            }
        });
    }

    private void stop() {
        if (medPlay.isPlaying()){
            medPlay.stop();
            try {
                medPlay.prepare();
            }catch (Exception e){
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }
    }

    private void pause() {
        if (medPlay.isPlaying()){
            medPlay.pause();
        }
    }

    private void start() {
        if (!medPlay.isPlaying()){
            medPlay.start();
            medPlay.setLooping(true);
        }

    }

    private void cargarControles() {
        btnPlay=findViewById(R.id.btnPl);
        btnPause=findViewById(R.id.btnPa);
        btnStop=findViewById(R.id.btnSt);
    }
}
