package com.mrk2.u4_pr01_floatbutton;
import androidx.appcompat.app.AppCompatActivity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

public class video_player extends AppCompatActivity {
    MediaPlayer medPlay;
    SurfaceView tablaDeSurf;
    SurfaceHolder sfHolder; //Adapter for visualize the video file
    Uri myUri; //extern routes

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        tablaDeSurf = findViewById(R.id.surfaceView);
        thaVideo();
    }

    private void thaVideo() {
        myUri = Uri.parse("android.resource://" + getPackageName()+"/"+R.raw.v2);
        medPlay = new MediaPlayer();
        medPlay.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            medPlay.setLooping(true);
            medPlay.setDataSource(getApplicationContext(),myUri);
            sfHolder = tablaDeSurf.getHolder();
            tablaDeSurf.setKeepScreenOn(true);
            sfHolder.addCallback(new SurfaceHolder.Callback() {
                @Override
                public void surfaceCreated(SurfaceHolder holder) {
                    medPlay.setDisplay(holder);
                    try {
                        medPlay.prepare();
                    }catch (Exception e){
                        e.printStackTrace();
                        Toast.makeText(video_player.this.getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    medPlay.start();
                }

                @Override
                public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

                }
                @Override
                public void surfaceDestroyed(SurfaceHolder holder) {
                    medPlay.stop();
                }
            });

        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
