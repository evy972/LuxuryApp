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


public class LoginActivity extends AppCompatActivity {

    DatabaseReference databaseUsers;


    private TextView mUsernameField;
    private TextView mPasswordField;

    String username;
    String password;

    private Button mLoginBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        mUsernameField = (EditText) findViewById(R.id.editText_email);
        mPasswordField = (EditText) findViewById(R.id.editText_password);




        mLoginBtn = (Button) findViewById(R.id.Login);

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = mUsernameField.getText().toString();
                password = mPasswordField.getText().toString();



                databaseUsers = FirebaseDatabase.getInstance().getReference("users");
                ValueEventListener valueEventListener = databaseUsers.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {



                        for (DataSnapshot d : dataSnapshot.getChildren()) {

                            String Key = d.getKey();
                            String Value = d.getValue().toString();


                            String mUsername = d.getValue().toString().substring(Value.indexOf('=') + 1, Value.indexOf(','));


                            String mPassword = Value.substring(Value.lastIndexOf('=') + 1);
                            password = password + '}';



                            if(mUsername.equals(username) && mPassword.equals(password))
                            {
                                Toast p = Toast.makeText(LoginActivity.this,"Hello " + mUsername + " " + "Welcome aboard!", Toast.LENGTH_LONG);
                                p.show();

                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                startActivity(intent);

                            }


                        }

                        Toast p = Toast.makeText(LoginActivity.this,"Please check your username & password", Toast.LENGTH_LONG);
                        p.show();
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
        User user = new User(name, email);

        databaseUsers.child("users").child(userId).setValue(user);
    }

}





/*



                String mUsername = myRef.child("username").getKey();
                Toast pieceToast= Toast.makeText(getApplicationContext(), mUsername, Toast.LENGTH_SHORT);
                pieceToast.show();
                String mPassword = myRef.child("password").getKey();
                 pieceToast= Toast.makeText(getApplicationContext(), mPassword, Toast.LENGTH_SHORT);
                pieceToast.show();

                if(mUsername.equals(username) && mPassword.equals(password))
                {
                     Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
else{
                     pieceToast= Toast.makeText(getApplicationContext(), "Please check your username & password", Toast.LENGTH_SHORT);
                    pieceToast.show();
                }

            }

        });
 */




/*
////1. extract username and password
2. get over the database and check if username equal to username
3. and password equal to password
if yes login
else not


 */



// Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
//startActivity(intent);


/*
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    public void signIn(View view)
    {
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
}
*/
