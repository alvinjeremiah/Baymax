package com.example.baymax;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.widget.Toolbar;

public class happy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_happy);



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