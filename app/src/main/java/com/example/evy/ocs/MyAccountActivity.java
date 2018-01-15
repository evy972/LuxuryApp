package com.example.evy.ocs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.StringSignature;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


/**
 * MyAccountActivity is responsible to show user profile details to the screen
 */

public class MyAccountActivity extends AppCompatActivity {

    //Define Variables
    TextView name_textView;
    TextView nickName_textView;
    TextView city_textView;
    TextView email_textView;
    ImageView image;

    String name;
    String nickName;
    String city;
    String email;

    private Button mEditBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        initVariables();
    }


    private void initVariables() {

        name_textView = (TextView) findViewById(R.id.ma_name_text);
        nickName_textView = (TextView) findViewById(R.id.ma_nickname_text);
        city_textView = (TextView) findViewById(R.id.ma_city_text);
        email_textView = (TextView) findViewById(R.id.ma_email_text);
        image = (ImageView) findViewById(R.id.ma_image);

        name_textView.setText(LoginActivity.Name);
        nickName_textView.setText(LoginActivity.username);
        city_textView.setText(LoginActivity.City);
        email_textView.setText(LoginActivity.Email);

        //make a connection to the database
        FirebaseStorage storage = FirebaseStorage.getInstance("gs://luxurycarstore-701a9.appspot.com");
        StorageReference storageRef = storage.getReference();

        //refernce to the unique key who logged in
        StorageReference mountainsRef = storageRef.child("images/" + LoginActivity.user.UID + ".jpg");


        //download image and set to the image component
        Glide.with(MyAccountActivity.this)
                .using(new FirebaseImageLoader())
                .load(mountainsRef).override(400, 400)
                .signature(new StringSignature(String.valueOf(System.currentTimeMillis())))
                .into(image);

        name = "";
        nickName = "";
        city = "";
        email = "";
    }


    private void initEdit() {
        mEditBtn.setOnClickListener(new View.OnClickListener(
        ) {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyAccountActivity.this, EditDiteailsActivity.class);
                startActivity(intent);
            }
        });
    }

    public void edit(View view) {
        Intent intent = new Intent(MyAccountActivity.this, EditDiteailsActivity.class);
        startActivity(intent);
    }
}


