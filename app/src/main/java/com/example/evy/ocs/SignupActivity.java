package com.example.evy.ocs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.firebase.client.Firebase;
import java.util.UUID;


/**
 * SignupActivity is responsible to connect a new user
 * to the application
 */
public class SignupActivity extends AppCompatActivity {

    //Define variables
    private EditText mUsername;
    private EditText mName;
    private EditText mCity;
    private EditText mEmail;
    private EditText mPassword;
    private Button nextBtn;

    static User user;

    //refernce to the database
    private Firebase mRootRefUsers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //Initialize variables
        mUsername = (EditText) findViewById(R.id.username_text);
        mName = (EditText) findViewById(R.id.name_text);
        mCity = (EditText) findViewById(R.id.city_text);
        mEmail = (EditText) findViewById(R.id.email_text);
        mPassword = (EditText) findViewById(R.id.password_text);
        nextBtn = (Button) findViewById(R.id.nextBTN);

        Firebase.setAndroidContext(this);
        mRootRefUsers = new Firebase("https://luxurycarstore-701a9.firebaseio.com/users");

        //listener to the confirm button
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
            }
        });
    }


    /**
     * signUp is responsible to create a new user
     * and to add him to the application user lists
     */
    private void signUp() {
        //Generating Unique Key for each user
        String unique_key = UUID.randomUUID().toString();

        //creating a child with unique key
        Firebase usersRef = mRootRefUsers.child(unique_key);

        //extract user input
        String username = mUsername.getText().toString();
        String name = mName.getText().toString();
        String city = mCity.getText().toString();
        String password = mPassword.getText().toString();
        String email = mEmail.getText().toString();

        //write the object
        user = new User(unique_key, username, password, name, city, email);
        usersRef.setValue(new User(username, password, name, city, email));


        //pass to the next activity
        Intent intent = new Intent(this, UploadPicActivity.class);
        startActivity(intent);
    }

    public void next(View view) {
        Intent intent = new Intent(SignupActivity.this, UploadPicActivity.class);
        startActivity(intent);
    }
}


