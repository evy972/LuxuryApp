package com.example.evy.ocs;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class GalleryActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int RESULT_LOAD_IMAGE = 1;

    ImageView imageUpload;
    Button bUpload;

    public void next(View view)
    {
        Intent intent = new Intent(GalleryActivity.this, SignedUpActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        imageUpload = (ImageView) findViewById(R.id.imageUpload);
        bUpload = (Button) findViewById(R.id.bUpload);

        imageUpload.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

         Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                 android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
         startActivityForResult(pickPhoto , 1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        if(resultCode == RESULT_OK){
            Uri selectedImage = imageReturnedIntent.getData();

            ContentResolver cR = this.getContentResolver();
            MimeTypeMap mime = MimeTypeMap.getSingleton();
            String type = mime.getExtensionFromMimeType(cR.getType(selectedImage));


            if(type.equals("jpg") || type.equals("png"))
            {
                Toast.makeText(GalleryActivity.this, "Image uploaded successfully",
                        Toast.LENGTH_LONG).show();
                imageUpload.setImageURI(selectedImage);
            }

            else
            {
                Toast.makeText(GalleryActivity.this, "Please select an image!",
                        Toast.LENGTH_LONG).show();
            }

        }

    }
}
