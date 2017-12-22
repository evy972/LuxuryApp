package com.example.evy.ocs;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class CameraActivity extends AppCompatActivity implements View.OnClickListener  {

    private static final int RESULT_LOAD_IMAGE = 1;

    ImageView imageUpload;
    Button bUpload;

    public void next(View view)
    {
        Intent intent = new Intent(CameraActivity.this, SignedUpActivity.class);
        startActivity(intent);
    }

    String path = Environment.getExternalStorageDirectory() + "/path/image.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        imageUpload = (ImageView) findViewById(R.id.imageUpload);
        bUpload = (Button) findViewById(R.id.bUpload);

        imageUpload.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePicture, 1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        if (imageReturnedIntent == null){
            Log.d("bitmap", "Bitmap is null!");}
        if(resultCode == RESULT_OK){

            Bitmap myBmp = (Bitmap) imageReturnedIntent.getExtras().get("data");
            imageUpload.setImageBitmap(myBmp);
            Toast.makeText(getApplicationContext(), "Image uploaded successfully", Toast.LENGTH_SHORT).show();

        }

    }
}
