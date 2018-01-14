/*
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.*;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

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
*/

/*
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.*;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

public class CameraActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int RESULT_LOAD_IMAGE = 1;

    ImageView imageUpload;
    Button bUpload;
    private Bitmap myBmp;



    String path = Environment.getExternalStorageDirectory() + "/path/image.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        imageUpload = (ImageView) findViewById(R.id.imageUpload);
        bUpload = (Button) findViewById(R.id.bUpload);

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

    private void upload() {
        if (myBmp != null) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            myBmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] data = baos.toByteArray();

            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference storageRef = storage.getReferenceFromUrl("gs://luxurycarstore-701a9.appspot.com");
            StorageReference imagesRef = storageRef.child("images/" + SignupActivity.user.UID + ".jpg");
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
        }
        else
        {

            Toast.makeText(this, "error on upload to storage ", Toast.LENGTH_LONG).show();
        }
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

            myBmp = (Bitmap) imageReturnedIntent.getExtras().get("data");
            imageUpload.setImageBitmap(myBmp);
            Toast.makeText(getApplicationContext(), "Image uploaded successfully", Toast.LENGTH_SHORT).show();

        }

    }


}
*/

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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.*;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

public class CameraActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int RESULT_LOAD_IMAGE = 1;

    ImageView imageUpload;
    Button bUpload;
    private Bitmap myBmp;



    String path = Environment.getExternalStorageDirectory() + "/path/image.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        imageUpload = (ImageView) findViewById(R.id.imageUpload);
        bUpload = (Button) findViewById(R.id.bUpload);

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

    private void upload() {
        if (myBmp != null) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            myBmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] data = baos.toByteArray();

            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference storageRef = storage.getReferenceFromUrl("gs://luxurycarstore-701a9.appspot.com");

            String userUID = "";
            if(SignupActivity.user != null)
            {
                userUID = SignupActivity.user.UID;
            }
            else userUID = LoginActivity.user.UID;



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
        }
        else
        {

            Toast.makeText(this, "error on upload to storage ", Toast.LENGTH_LONG).show();
        }
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

            myBmp = (Bitmap) imageReturnedIntent.getExtras().get("data");
            imageUpload.setImageBitmap(myBmp);
            Toast.makeText(getApplicationContext(), "Image uploaded successfully", Toast.LENGTH_SHORT).show();

        }

    }





}