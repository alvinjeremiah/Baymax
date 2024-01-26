package com.example.baymax;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class unhappy extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unhappy);

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
