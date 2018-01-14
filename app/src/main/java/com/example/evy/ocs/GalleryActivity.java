/*
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
*/

/*
package com.example.evy.ocs;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

public class GalleryActivity extends AppCompatActivity {



    //Variables
    private Button btnChoose, btnUpolad;
    private ImageView imageView;
    private Uri filePath;

    private final int PICK_IMAGE_REQUEST = 90;


    //FireBase Storage
    FirebaseStorage storage;
    StorageReference mStorageReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        //Toast.makeText(GalleryActivity.this, "Image Uploaded Successfully", Toast.LENGTH_LONG).show();
        //Initialize View
        btnChoose = (Button) findViewById(R.id.bSelectImage);
        btnUpolad = (Button) findViewById(R.id.bUpload);
        imageView = (ImageView) findViewById(R.id.imageShow);

        //Firebase storage init
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




    private void uploadImage()
    {
        if(filePath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();


            //StorageReference ref = mStorageReference.child("Images/" + "Space");


            StorageReference storageRef = storage.getReferenceFromUrl("gs://luxurycarstore-701a9.appspot.com");

// Create a reference to "file"
            String unique_key = UUID.randomUUID().toString();

            StorageReference mountainsRef = storageRef.child("images/" + SignupActivity.user.UID + ".jpg");

            //StorageReference mountainsRef = storageRef.child("Images/" + unique_key+".jpg");
            //'' Images/'' + user. UID/ + profile. Jpg
            //"Images/" + "file.jpg"


            mountainsRef.putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    progressDialog.dismiss();
                    Toast.makeText(GalleryActivity.this, "Image Uploaded Successfully", Toast.LENGTH_SHORT).show();

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
                            double progress = (100 * taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                            progressDialog.setMessage("Image Uploaded" + (int)progress + "%");
                        }
                    });
        }
    }

    private void chooseImage()
    {


        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent , PICK_IMAGE_REQUEST);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null)
        {
            filePath = data.getData();
            try
            {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

        }

    }



    public void next(View view) {
        Intent intent = new Intent(GalleryActivity.this, SignedUpActivity.class);
        startActivity(intent);
    }

}

*/

/*
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
*/

/*
package com.example.evy.ocs;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class GalleryActivity extends AppCompatActivity {



    //Variables
    private Button btnChoose, btnUpolad;
    private ImageView imageView;

    private Uri filePath;

    private final int PICK_IMAGE_REQUEST = 90;


    //FireBase Storage
    FirebaseStorage storage;
    StorageReference mStorageReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        //Toast.makeText(GalleryActivity.this, "Image Uploaded Successfully", Toast.LENGTH_LONG).show();
        //Initialize View
        btnChoose = (Button) findViewById(R.id.bSelectImage);
        btnUpolad = (Button) findViewById(R.id.bUpload);
        imageView = (ImageView) findViewById(R.id.imageShow);

        //Firebase storage init
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




    private void uploadImage()
    {
        if(filePath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();


            //StorageReference ref = mStorageReference.child("Images/" + "Space");


            StorageReference storageRef = storage.getReferenceFromUrl("gs://luxurycarstore-701a9.appspot.com");

// Create a reference to "file"
            StorageReference mountainsRef = storageRef.child("images/" + SignupActivity.user.UID + ".jpg");
            //'' Images/'' + user. UID/ + profile. Jpg
            //"Images/" + "file.jpg"


            mountainsRef.putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    progressDialog.dismiss();
                    Toast.makeText(GalleryActivity.this, "Image Uploaded Successfully", Toast.LENGTH_SHORT).show();
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
                            double progress = (100 * taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                            progressDialog.setMessage("Image Uploaded" + (int)progress + "%");
                        }
                    });
        }
    }

    private void chooseImage()
    {


        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent , PICK_IMAGE_REQUEST);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null)
        {
            filePath = data.getData();
            try
            {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

        }

    }





}
*/

package com.example.evy.ocs;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class GalleryActivity extends AppCompatActivity {



    //Variables
    private Button btnChoose, btnUpolad;
    private ImageView imageView;

    private Uri filePath;

    private final int PICK_IMAGE_REQUEST = 90;


    //FireBase Storage
    FirebaseStorage storage;
    StorageReference mStorageReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        //Toast.makeText(GalleryActivity.this, "Image Uploaded Successfully", Toast.LENGTH_LONG).show();
        //Initialize View
        btnChoose = (Button) findViewById(R.id.bSelectImage);
        btnUpolad = (Button) findViewById(R.id.bUpload);
        imageView = (ImageView) findViewById(R.id.imageShow);

        //Firebase storage init
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




    private void uploadImage()
    {
        if(filePath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();


            //StorageReference ref = mStorageReference.child("Images/" + "Space");


            StorageReference storageRef = storage.getReferenceFromUrl("gs://luxurycarstore-701a9.appspot.com");

            String userUID = "";
            if(SignupActivity.user != null)
            {
                userUID = SignupActivity.user.UID;
            }
            else userUID = LoginActivity.user.UID;


            StorageReference mountainsRef = storageRef.child("images/" + userUID + ".jpg");


            mountainsRef.putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    progressDialog.dismiss();
                    Toast.makeText(GalleryActivity.this, "Image Uploaded Successfully", Toast.LENGTH_SHORT).show();
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
                            double progress = (100 * taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                            progressDialog.setMessage("Image Uploaded" + (int)progress + "%");
                        }
                    });
        }
    }

    private void chooseImage()
    {


        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent , PICK_IMAGE_REQUEST);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null)
        {
            filePath = data.getData();
            try
            {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

        }

    }





}


