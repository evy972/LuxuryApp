package com.example.evy.ocs;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.view.View;
import android.net.Uri;


public class UploadPicActivity extends AppCompatActivity  {

    private static final int RESULT_LOAD_IMAGE = 1;

    ImageView imageUpload;
    Button bUpload;


    public void gallary(View view)
    {
        Intent intent = new Intent(UploadPicActivity.this, GalleryActivity.class);
        startActivity(intent);
    }

    public void camera(View view)
    {
        Intent intent = new Intent(UploadPicActivity.this, CameraActivity.class);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_pic);

    }

}