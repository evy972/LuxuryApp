package com.example.evy.ocs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * SignedUpActivity is responsible to pass the application changed details
 */

public class SignedUpActivity extends AppCompatActivity {

    public void Continue(View view) {
        //move to the next screen
        Intent intent = new Intent(SignedUpActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signed_up);
    }
}
