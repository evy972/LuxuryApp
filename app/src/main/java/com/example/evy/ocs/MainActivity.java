package com.example.evy.ocs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;

import com.google.firebase.analytics.FirebaseAnalytics;

/**
 * MainActivity is the application first screen
 */
public class MainActivity extends AppCompatActivity {

    // analytics: object declaration at the top of activity
    private FirebaseAnalytics mFirebaseAnalytics;

    private Button signInBtn;
    private Button signUpBtn;
    private Button abbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // connect the layout with this java file

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        signInBtn = findViewById(R.id.sign_in_btn);// connect the button to layout component
        signUpBtn = findViewById(R.id.sign_up_btn);
        abbtn = findViewById(R.id.about_btn);


        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });

        abbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                about();
            }
        });

    } // onCreate


    public void signIn() {
        // analytics
        Bundle bundle = new Bundle();
        bundle.putInt("SIGN_IN_STATS", R.id.sign_in_btn);
        // this section must come after bundle
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SEARCH, bundle);
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);



        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    public void signUp() {
        // analytics
        Bundle bundle = new Bundle();
        bundle.putInt("SIGN_UP_STATS", R.id.sign_up_btn);
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SEARCH, bundle);
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);


        Intent intent = new Intent(MainActivity.this, SignupActivity.class);
        startActivity(intent);
    }


    public void map(View view) {
        // analytics
        Bundle bundle = new Bundle();
        bundle.putInt("MAP_STATS", R.id.buttonMap);
        // this section must come after bundle
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SEARCH, bundle);
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);


        Intent intent = new Intent(MainActivity.this, MapsActivity.class);
        startActivity(intent);
    }

    public void about() {
        // analytics
        Bundle bundle = new Bundle();
        bundle.putInt("ABOUT_STATS", R.id.about_btn);
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SEARCH, bundle);
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);


        Intent intent = new Intent(MainActivity.this, AboutActivity.class);
        startActivity(intent);
    }

}
