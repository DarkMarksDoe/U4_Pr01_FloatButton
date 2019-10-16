package com.mrk2.u4_pr01_floatbutton;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

public class ActivityVideoView extends AppCompatActivity {

    VideoView videoView;
    MediaController myControler;

    Uri myUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);
       cargarVideo();
    }

    private void cargarVideo() {
        videoView = findViewById(R.id.videoView);
        myUri = Uri.parse("android.resource://" + getPackageName()+"/"+R.raw.v1);
        if(isLandscape()){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            videoView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_IMMERSIVE
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            );
            getSupportActionBar().hide();
            myControler = new MediaController(this);
            myControler.setAnchorView(videoView);
            videoView.setVideoURI(myUri);
            videoView.start();
            videoView.requestFocus();
            videoView.setMediaController(myControler);
        }else{
            myControler = new MediaController(this);
            videoView.setVideoURI(myUri);
            videoView.start();
            videoView.requestFocus();
            videoView.setMediaController(myControler);
        }

    }

    private boolean isLandscape() {
        int currentScreen = getResources().getConfiguration().orientation;
        if(currentScreen == Configuration.ORIENTATION_LANDSCAPE){
            return true;
        }else{
            return false;
        }
    }
}
