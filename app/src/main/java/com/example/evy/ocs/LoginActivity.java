package com.example.evy.ocs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.HashMap;

/**
 * LoginActivity is responsible for verification between the user and the database
 */
public class LoginActivity extends AppCompatActivity {

    //Creation of reference to a DB
    DatabaseReference databaseUsers;

    // Text object which relates to layout input component
    // user inserts his info via this object in the layout section
    private TextView mUsernameField;
    private TextView mPasswordField;

    // Button object which relates to layout button component
    private Button mLoginBtn;

    //Define Variables
    static String username;
    static String password;
    static String UID;
    static String Name;
    static String Email;
    static String City;
    boolean isAuth;

    // shared object that keeps the authentication info of the logged in user
    static User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); // connect the layout with this java file

        isAuth = false;

        mUsernameField = (EditText) findViewById(R.id.editText_email);
        mPasswordField = (EditText) findViewById(R.id.editText_password);

        mLoginBtn = (Button) findViewById(R.id.Login);

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }


    /**
     * login function attempts to make authentication between
     * the user and the application via the database
     */
    private void login() {
        //extract the user details from the EditText layout component
        username = mUsernameField.getText().toString().trim();
        password = mPasswordField.getText().toString().trim();

        //get access to the database in the suitable table
        databaseUsers = FirebaseDatabase.getInstance().getReference("users");

        ValueEventListener valueEventListener = databaseUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot d : dataSnapshot.getChildren()) { //on every snapshot in the table

                    //create a data structure to extract user fields
                    HashMap<String, String> value = (HashMap<String, String>) d.getValue();
                    String usernameCheck = value.get("username");
                    String passwordCheck = value.get("password");


                    //checks if the username & password are identical to the input
                    if (usernameCheck.equals(username) && passwordCheck.equals(password)) {
                        //extract each field
                        Email = value.get("Email");
                        Name = value.get("Name");
                        City = value.get("City");
                        username = value.get("username");
                        password = value.get("password");
                        UID = d.getKey();
                        //call to the user constructor with the values
                        user = new User(UID, username, password, Name, City, Email);

                        isAuth = true;

                        //print to the screen success message
                        Toast p2 = Toast.makeText(LoginActivity.this, "Welcome " + usernameCheck + " " + "!", Toast.LENGTH_LONG);
                        p2.show();

                        //move to the next screen
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);
                    }
                }

                if (isAuth == false) {
                    Toast p = Toast.makeText(LoginActivity.this, "Please check your username & password", Toast.LENGTH_LONG);
                    p.show();
                }
                //reset the text fields in the layout
                mUsernameField.setText("");
                mPasswordField.setText("");
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                //if the attempt to connect to db was failed
                Toast p = Toast.makeText(LoginActivity.this, "Access denied!", Toast.LENGTH_LONG);
                p.show();
            }
        });
    }
}



