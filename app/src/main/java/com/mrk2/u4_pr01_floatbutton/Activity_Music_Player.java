package com.mrk2.u4_pr01_floatbutton;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FilenameFilter;
import java.util.concurrent.TimeUnit;

public class Activity_Music_Player extends AppCompatActivity {

    ImageButton next, preview, play;
    ProgressBar musicBar;
    TextView start, end;
    ImageView album;

    private File audioFiles;
    private File[] audioList;
    private MediaPlayer medPlay;
    int x = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__music__player);
        controls();
        music();
        events();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(medPlay!=null){
            if (!medPlay.isPlaying()){
                medPlay.start();
                medPlay.setLooping(true);
            }
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (medPlay.isPlaying()){
            medPlay.stop();
            try {
                medPlay.prepare();
            }catch (Exception e){
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (medPlay.isPlaying()){
            medPlay.pause();
        }
    }

    private void music() {
        try {
            FilenameFilter musicFilter = new FilenameFilter() {
                @Override
                public boolean accept(File file, String nomFile) {
                    if (nomFile.endsWith(".mp3")) {
                        return true;
                    } else {
                        return false;
                    }
                }
            };
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                audioFiles = new File(Environment.getExternalStorageDirectory() + File.separator + "Music/Audios");
                if (audioFiles.isDirectory()) {
                    audioList = audioFiles.listFiles(musicFilter);
                } else {
                    Toast.makeText(this, "Is not a directory", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    private void updatePlayer(int currentDuration){
        //End Text
        long min = TimeUnit.MILLISECONDS.toMinutes(currentDuration);
        long sec = TimeUnit.MILLISECONDS.toSeconds(currentDuration-TimeUnit.MINUTES.toMillis(currentDuration));
        end.setText(min+":"+sec);
    }
    private void events() {

        //Play button
        play.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                try {
                    if (medPlay != null) {
                        //First Time
                        playing();

                    }
                    {
                        pausePlay();
                    }
                } catch (Exception e) {
                    Toast.makeText(Activity_Music_Player.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Next button
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x++;
                if (x == audioList.length) {
                    x = 0;
                }
                playing();
            }
        });

        //Preview button
        preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x--;
                if (x < 0) {
                    x = audioList.length - 1;
                }
                playing();
            }
        });


    }

    private void pausePlay() {

    }

    private void playing() {
        try {
            if (medPlay != null) {
                medPlay.release();
            }
            medPlay = new MediaPlayer();
            medPlay.setAudioStreamType(AudioManager.STREAM_MUSIC);
            medPlay.setDataSource(audioList[x].getPath());
            medPlay.prepare();
            updatePlayer(medPlay.getDuration());
            medPlay.seekTo(0);

            medPlay.start();
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void controls() {
        next = findViewById(R.id.next_song);
        preview = findViewById(R.id.preview_song);
        play = findViewById(R.id.play_song);
        musicBar = findViewById(R.id.size_audio);
        start = findViewById(R.id.textIn);
        end = findViewById(R.id.textOut);
        album = findViewById(R.id.image_Album);
    }
}
