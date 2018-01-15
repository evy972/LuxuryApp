package com.example.evy.ocs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


/**
 * HomeActivity is responsible to navigate the login user screen
 */

public class HomeActivity extends AppCompatActivity {


    public void about(View view) {
        //moving to the next screen
        Intent intent = new Intent(HomeActivity.this, AboutActivity.class);
        startActivity(intent);
    }

    public void map(View view) {
        //moving to the next screen
        Intent intent = new Intent(HomeActivity.this, MapsActivity.class);
        startActivity(intent);
    }

    public void myAccunt(View view) {
        //moving to the next screen
        Intent intent = new Intent(HomeActivity.this, MyAccountActivity.class);
        startActivity(intent);
    }

    public void products(View view) {
        //moving to the next screen
        Intent intent = new Intent(HomeActivity.this, ProductsActivity.class);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    }
}
