package com.example.evy.ocs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;

import java.util.UUID;

import com.firebase.client.Firebase;
public class SignupActivity extends AppCompatActivity {

    private EditText mUsername;
    private EditText mName;
    private EditText  mCity;
    private EditText mEmail;
    private EditText mPassword;
    private Button nextBtn;
    static User user;

    //Refernce for the usersRoot
    private Firebase mRootRefUsers;
    //private Firebase mRootRefUserInfo;



    public void next(View view)
    {
        Intent intent = new Intent(SignupActivity.this, UploadPicActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mUsername = (EditText)findViewById(R.id.username_text);
        mName = (EditText)findViewById(R.id.name_text);
        mCity = (EditText)findViewById(R.id.city_text);
        mEmail = (EditText) findViewById(R.id.email_text);
        mPassword = (EditText)findViewById(R.id.password_text);
        nextBtn = (Button)findViewById(R.id.nextBTN);


        //firebase refernce init
        Firebase.setAndroidContext(this);
        mRootRefUsers = new Firebase("https://luxurycarstore-701a9.firebaseio.com/users");
        //mRootRefUserInfo = new Firebase("https://luxurycarstore-701a9.firebaseio.com/userInfo");

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
            }
        });
    }

    private void signUp()
    {

        //Generating Unique Key for each user
        String unique_key = UUID.randomUUID().toString();

        //creating a child with unique key
        Firebase usersRef = mRootRefUsers.child(unique_key);
        //Firebase userInfoRef = mRootRefUserInfo.child(unique_key);


        //extract user input
        String username = mUsername.getText().toString();
        String name = mName.getText().toString();
        String city = mCity.getText().toString();
        String password = mPassword.getText().toString();
        String email = mEmail.getText().toString();

        //write the object
        user = new User(unique_key,username, password, name ,city , email);
        usersRef.setValue(new User(username,password, name ,city , email));
        //userInfoRef.setValue(new User(name, city, email) );


        //pass to the next activity
        Intent intent = new Intent(this,UploadPicActivity.class);
        startActivity(intent);
    }
}
