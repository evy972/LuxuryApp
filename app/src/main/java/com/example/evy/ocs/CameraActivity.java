
package com.example.evy.ocs;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.*;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.io.ByteArrayOutputStream;

/**
 * CameraActivity is responsible to extract a photo to the user profile
 */

public class CameraActivity extends AppCompatActivity implements View.OnClickListener {

    //constant result
    private static final int RESULT_LOAD_IMAGE = 1;

    //Define variables
    ImageView imageUpload;
    Button bUpload;
    private Bitmap myBmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        imageUpload = (ImageView) findViewById(R.id.imageUpload);
        bUpload = (Button) findViewById(R.id.bUpload);

        // create a path to take a picture
        String path = Environment.getExternalStorageDirectory() + "/path/image.jpg";
        imageUpload.setOnClickListener(this);

        bUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upload();
                Intent intent = new Intent(CameraActivity.this, SignedUpActivity.class);
                startActivity(intent);
            }
        });
    }


    /**
     * onClick connects the user takes his picture
     */
    @Override
    public void onClick(View v) {
        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePicture, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        if (imageReturnedIntent == null) {
            Log.d("bitmap", "Bitmap is null!");
        }
        if (resultCode == RESULT_OK) {

            myBmp = (Bitmap) imageReturnedIntent.getExtras().get("data");
            imageUpload.setImageBitmap(myBmp);
            Toast.makeText(getApplicationContext(), "Image uploaded successfully", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * upload function make the connection to the firebase storage
     * and create an new image on the storage
     */
    private void upload() {
        if (myBmp != null) {
            //extract the picture and compress to a byte array
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            myBmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] data = baos.toByteArray();

            //create a refernce to the database storage
            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference storageRef = storage.getReferenceFromUrl("gs://luxurycarstore-701a9.appspot.com");

            //extract user unique key
            String userUID = "";
            if (SignupActivity.user != null) {
                userUID = SignupActivity.user.UID;
            } else userUID = LoginActivity.user.UID;

            //create a new photo with the unique user key
            StorageReference imagesRef = storageRef.child("images/" + userUID + ".jpg");
            UploadTask uploadTask = imagesRef.putBytes(data);
            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle unsuccessful uploads
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                    Uri downloadUrl = taskSnapshot.getDownloadUrl();

                }
            });
        } else {

            Toast.makeText(this, "error on upload to storage ", Toast.LENGTH_LONG).show();
        }
    }
}