package com.example.evy.ocs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.Firebase;

import java.util.UUID;

public class EditDiteailsActivity extends AppCompatActivity {

    public void save(View view)
    {
        Intent intent = new Intent(EditDiteailsActivity.this, MyAccountActivity.class);
        startActivity(intent);
    }

    private EditText mUsername;
    private EditText mName;
    private EditText  mCity;
    private EditText mEmail;
    private EditText mPassword;
    private Button saveBtn;

    private Firebase mRootRefUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_diteails);

        mUsername = (EditText)findViewById(R.id.edit_username_text);
        mName = (EditText)findViewById(R.id.edit_name_text);
        mCity = (EditText)findViewById(R.id.edit_city_text);
        mEmail = (EditText) findViewById(R.id.edit_email_text);
        mPassword = (EditText) findViewById(R.id.edit_password_text);
        saveBtn = (Button)findViewById(R.id.saveBTN);


        Firebase.setAndroidContext(this);
        mRootRefUsers = new Firebase("https://luxurycarstore-701a9.firebaseio.com/users");

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });
    }

    private void save()
    {

        Firebase usersRef = mRootRefUsers.child(LoginActivity.UID);

        //extract user input
        String username = mUsername.getText().toString();
        String name = mName.getText().toString();
        String city = mCity.getText().toString();
        String password = mPassword.getText().toString();
        String email = mEmail.getText().toString();

        //write the object
        LoginActivity.username = username;
        LoginActivity.Name = name;
        LoginActivity.City = city;
        LoginActivity.Email = email;
        LoginActivity.password = password;
        usersRef.setValue(new User(username, password, name ,city , email));
        //userInfoRef.setValue(new User(name, city, email) );


        //pass to the next activity
        Intent intent = new Intent(EditDiteailsActivity.this, MyAccountActivity.class);
        startActivity(intent);
    }
}
