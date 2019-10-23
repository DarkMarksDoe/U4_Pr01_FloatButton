package com.mrk2.u4_pr01_floatbutton;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FilenameFilter;

public class ActivityThingsPlayer extends AppCompatActivity {
    private File imageFile;
    private File[] audioFiles[];
    private File[] imageList;
    private File[] audioList;
    private MediaPlayer mediaPlayer;
    int i;
    ImageView imageView;
    ImageButton btnPreview, btnNext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_things_player);
        controls();
        buttons();
        addingData();
    }

    private void addingData() {

        try {
            //Adding Filter for images
            FilenameFilter filenameFilter = new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    if(name.endsWith(".jpg") || name.endsWith(".png") || name.endsWith(".jpeg")){
                        return true;
                    }else
                        return false;
                }
            };
            //Validate SDCard
            if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                imageFile = new File(Environment.getExternalStorageDirectory()+File.separator+"Pictures/Instagram");
                if (imageFile.isDirectory()){
                    //Apply filter for validate image
                    imageList= imageFile.listFiles(filenameFilter);
                }
            }
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    private void buttons() {
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextThing();
            }
        });
        btnPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previousThing();
            }
        });
    }

    private void previousThing() {
        i--;
        if(i==imageList.length){
            i = 0;
        }
        showImage();
    }

    private void nextThing() {
        i++;
        if(i==imageList.length){
            i =0;
        }
        showImage();
    }

    private void showImage() {
        try {
            if((BitmapDrawable)imageView.getDrawable()!=null){
                ((BitmapDrawable) imageView.getDrawable()).getBitmap().recycle();
            }
            //createBitmap
            imageView.setImageBitmap(BitmapFactory.decodeFile(imageList[i].getAbsolutePath()));
            //check if is charged
            Toast.makeText(this, imageList[i].getName(), Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    private void controls() {
        imageView = findViewById(R.id.imgImage);
        btnPreview = findViewById(R.id.atp_btn_preview);
        btnNext = findViewById(R.id.atp_btn_next);

    }
}
