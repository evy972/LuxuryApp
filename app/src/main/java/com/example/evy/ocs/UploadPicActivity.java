package com.example.evy.ocs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.view.View;


/**
 * UploadPicActivity is responsible to pick image from different choices
 * for the user convenience
 */
public class UploadPicActivity extends AppCompatActivity {

    private static final int RESULT_LOAD_IMAGE = 1;

    //Define variables
    ImageView imageUpload;
    Button bUpload;


    public void gallary(View view) {
        //move to the next screen
        Intent intent = new Intent(UploadPicActivity.this, GalleryActivity.class);
        startActivity(intent);
    }

    public void camera(View view) {
        //move to the next screen
        Intent intent = new Intent(UploadPicActivity.this, CameraActivity.class);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_pic);
    }
}