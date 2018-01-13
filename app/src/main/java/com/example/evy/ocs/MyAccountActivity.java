/*
package com.example.evy.ocs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MyAccountActivity extends AppCompatActivity {

    public void edit(View view)
    {
        Intent intent = new Intent(MyAccountActivity.this, EditDiteailsActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
    }
}
*/

package com.example.evy.ocs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StreamDownloadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

public class MyAccountActivity extends AppCompatActivity {

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

    //User user;

    public void edit(View view)
    {
        Intent intent = new Intent(MyAccountActivity.this, EditDiteailsActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);


        initVariables();


        //initEdit();
    }

    private void initEdit()
    {
        mEditBtn.setOnClickListener(new View.OnClickListener(
        )
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MyAccountActivity.this, EditDiteailsActivity.class);
                startActivity(intent);
            }
        });
    }


    private void initVariables()
    {

        name_textView = (TextView)findViewById(R.id.ma_name_text);
        nickName_textView = (TextView)findViewById(R.id.ma_nickname_text);
        city_textView = (TextView)findViewById(R.id.ma_city_text);
        email_textView = (TextView)findViewById(R.id.ma_email_text);
        image = (ImageView)findViewById(R.id.ma_image);

        name_textView.setText(LoginActivity.Name);
        nickName_textView.setText(LoginActivity.username);
        city_textView.setText(LoginActivity.City);
        email_textView.setText(LoginActivity.Email);

        Picasso.with(MyAccountActivity.this).load("https://firebasestorage.googleapis.com/v0/b/luxurycarstore-701a9.appspot.com/o/images%2Fc8a57026-a187-4a27-8543-3e3d33aa6cd0.jpg?alt=media&token=adf1dcac-b5f3-4aff-b75d-31e2b533c4a1").into(image);



        name = "";
        nickName = "";
        city = "";
        email = "";
        // user = new User();

    }

















}


