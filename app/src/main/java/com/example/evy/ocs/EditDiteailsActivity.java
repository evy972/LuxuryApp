package com.example.evy.ocs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class EditDiteailsActivity extends AppCompatActivity {

    public void save(View view)
    {
        Intent intent = new Intent(EditDiteailsActivity.this, MyAccountActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_diteails);
    }
}
