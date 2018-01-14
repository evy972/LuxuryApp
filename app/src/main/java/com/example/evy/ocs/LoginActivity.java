
package com.example.evy.ocs;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;


public class LoginActivity extends AppCompatActivity {

    DatabaseReference databaseUsers;
    DatabaseReference databaseUserInfo;


    private TextView mUsernameField;
    private TextView mPasswordField;

    static String username;
    static String password;
    static String UID;


    static String Name;
    static String Email;
    static String City;

    boolean isAuth;
    static User user;
    private Button mLoginBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        isAuth = false;

        mUsernameField = (EditText) findViewById(R.id.editText_email);
        mPasswordField = (EditText) findViewById(R.id.editText_password);




        mLoginBtn = (Button) findViewById(R.id.Login);

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = mUsernameField.getText().toString().trim();
                password = mPasswordField.getText().toString().trim();


                databaseUsers = FirebaseDatabase.getInstance().getReference("users");
                ValueEventListener valueEventListener = databaseUsers.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {



                        for (DataSnapshot d : dataSnapshot.getChildren()) {


                            HashMap<String, String> value = (HashMap<String, String>) d.getValue();
                            String usernameCheck = value.get("username");

                            String passwordCheck = value.get("password");


                            if(usernameCheck.equals(username) && passwordCheck.equals(password))
                            {

                                Email = value.get("Email");
                                Name = value.get("Name");
                                City = value.get("City");
                                username = value.get("username");
                                password = value.get("password");
                                UID =  d.getKey();
                                user = new User(UID, username, password, Name, City, Email);
                                isAuth = true;
                                Toast p2 = Toast.makeText(LoginActivity.this,"Welcome " + usernameCheck + " " + "!", Toast.LENGTH_LONG);
                                p2.show();

                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                startActivity(intent);

                            }


                        }
                        if(isAuth == false)
                        {
                            Toast p = Toast.makeText(LoginActivity.this,"Please check your username & password", Toast.LENGTH_LONG);
                            p.show();
                        }
                        mUsernameField.setText("");
                        mPasswordField.setText("");
                    }




                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast p = Toast.makeText(LoginActivity.this,"Access denied!", Toast.LENGTH_LONG);
                        p.show();
                    }
                });


            }


        });
    }


    private void writeNewUser(String userId, String name, String email) {
        user = new User(UID, username, password, Name, City, Email);

    }

}



