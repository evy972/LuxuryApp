package com.example.evy.ocs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.firebase.client.Firebase;


/**
 * EditdetailsActivity is responsible to connect to the database and
 * change user details
 */

public class EditDiteailsActivity extends AppCompatActivity {
    //Define Variables
    private EditText mUsername;
    private EditText mName;
    private EditText mCity;
    private EditText mEmail;
    private EditText mPassword;
    private Button saveBtn;
    private Button changePhotoBtn;

    // Define refernce to the database root
    private Firebase mRootRefUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_diteails);

        //Initailize variables
        mUsername = (EditText) findViewById(R.id.edit_username_text);
        mName = (EditText) findViewById(R.id.edit_name_text);
        mCity = (EditText) findViewById(R.id.edit_city_text);
        mEmail = (EditText) findViewById(R.id.edit_email_text);
        mPassword = (EditText) findViewById(R.id.edit_password_text);
        saveBtn = (Button) findViewById(R.id.saveBTN);
        changePhotoBtn = (Button) findViewById(R.id.image_change_btn);

        Firebase.setAndroidContext(this);
        mRootRefUsers = new Firebase("https://luxurycarstore-701a9.firebaseio.com/users");

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });

        changePhotoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditDiteailsActivity.this, UploadPicActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Save function attempts to connect to the database
     * and to change the details by the input
     */
    private void save() {
        //make a refernce by the unique key
        Firebase usersRef = mRootRefUsers.child(LoginActivity.UID);

        //extract user fields from the layout components
        String username = mUsername.getText().toString();
        String name = mName.getText().toString();
        String city = mCity.getText().toString();
        String password = mPassword.getText().toString();
        String email = mEmail.getText().toString();


        //taking the edge case if the user input is null
        if (!username.isEmpty()) LoginActivity.username = username;
        else username = LoginActivity.username;

        if (!name.isEmpty()) LoginActivity.Name = name;
        else name = LoginActivity.Name;

        if (!city.isEmpty()) LoginActivity.City = city;
        else city = LoginActivity.City;

        if (!email.isEmpty()) LoginActivity.Email = email;
        else email = LoginActivity.Email;

        if (!password.isEmpty()) LoginActivity.password = password;
        else password = LoginActivity.password;

        //set the user fields in the database
        usersRef.setValue(new User(username, password, name, city, email));
    }
}
