package com.example.evy.ocs;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.io.IOException;


/**
 * GalleryActivity is responsible to pick a photo from
 * the gallery on the device storage
 */
public class GalleryActivity extends AppCompatActivity {

    //constant result
    private final int PICK_IMAGE_REQUEST = 90;

    //Define variables
    private Button btnChoose, btnUpolad;
    private ImageView imageView;
    private Uri filePath;
    FirebaseStorage storage;
    StorageReference mStorageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        //Initialize vairables
        btnChoose = (Button) findViewById(R.id.bSelectImage);
        btnUpolad = (Button) findViewById(R.id.bUpload);
        imageView = (ImageView) findViewById(R.id.imageShow);
        storage = FirebaseStorage.getInstance();

        btnUpolad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                uploadImage();
            }
        });
        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImage();
            }
        });
    }


    /**
     * chooseImage function make the connection from the application
     * to the storage and picking image from the gallery storage
     */
    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData(); //extract image by the path
            try {
                //extract data to the bitmap object to represent the picture
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                //show the image to the screen
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * upload function make the connection to the firebase storage
     * and create an new image on the storage
     */
    private void uploadImage() {
        if (filePath != null) {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            //create a refernce to the database storage
            StorageReference storageRef = storage.getReferenceFromUrl("gs://luxurycarstore-701a9.appspot.com");

            //extract user unique key
            String userUID = "";
            if (SignupActivity.user != null) {
                userUID = SignupActivity.user.UID;
            } else userUID = LoginActivity.user.UID;

            //create a new photo with the unique user key
            StorageReference mountainsRef = storageRef.child("images/" + userUID + ".jpg");


            mountainsRef.putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    progressDialog.dismiss();
                    Toast.makeText(GalleryActivity.this, "Image Uploaded Successfully", Toast.LENGTH_SHORT).show();
                    //on success move to the next screen
                    Intent intent = new Intent(GalleryActivity.this, SignedUpActivity.class);
                    startActivity(intent);

                }

            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(GalleryActivity.this, "Image Uploaded Failed" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            progressDialog.setMessage("Image Uploaded" + (int) progress + "%");
                        }
                    });
        }
    }
}


