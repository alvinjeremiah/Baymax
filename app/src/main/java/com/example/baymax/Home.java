package com.example.baymax;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.ktx.Firebase;

import java.util.Calendar;
import java.util.Date;


public class Home extends AppCompatActivity {

    FirebaseAuth auth;
    BottomAppBar bottomAppBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth= FirebaseAuth.getInstance();
        setContentView(R.layout.activity_home);


        if (bottomAppBar != null) {
            // Only try to call methods if bottomAppBar is not null
            bottomAppBar.replaceMenu(R.menu.bottom_app_bar_menu);
        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        TextView textMessage = findViewById(R.id.textView3);

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        if (account != null) {
            // Get the user's display name
            String userName = account.getDisplayName();

            // Get the time of day
            Date date = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int hour = cal.get(Calendar.HOUR_OF_DAY);

            // Set greeting
            String greeting = "Hello";
            if (hour >= 6 && hour < 12) {
                greeting = "Good Morning!";
            } else if (hour >= 12 && hour < 17) {
                greeting = "Good Afternoon!";
            } else if (hour >= 17 && hour < 24) {
                greeting = "Good Evening!";
            }

            // Set the string being displayed by the TextView to the greeting
            // message for the user
            textMessage.setText(greeting + " " + userName + ", How are you doing today?");
        }
    }





    public void onHappy(View view){
        Intent i = new Intent(this, happy.class);
        startActivity(i);

    }

    public void onSad(View view){
        Intent i = new Intent(this, unhappy.class);
        startActivity(i);

    }
    public void onReminder(View view){
        Intent i = new Intent(this, reminder.class);
        startActivity(i);

    }

    public void onSettings(View view){
        Intent i = new Intent(this, settings.class);
        startActivity(i);

    }

}

