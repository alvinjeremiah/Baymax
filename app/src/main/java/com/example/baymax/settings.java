package com.example.baymax;

import static android.app.ProgressDialog.show;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;

public class settings extends AppCompatActivity {

    private LinearLayout dynamicContainer;
    private LinearLayout addEmergencyContact;

    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);



        auth = FirebaseAuth.getInstance();

        TextView doctorDetailsEditText = findViewById(R.id.textView12);

        doctorDetailsEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the click event, show a dialog, or perform any other action
                showAddDoctorDialog();
            }
        });

        // Set the click listener for 'Emergency Contact'
        TextView emergencyContactTextView = findViewById(R.id.textView17);
        emergencyContactTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the click event, and show the custom dialog for emergency contact
                showAddEmergencyContactDialog();
            }
        });
    }

    private void showAddDoctorDialog() {
        // Inflate the dialog layout
        View dialogView = LayoutInflater.from(this).inflate(R.layout.adddoc, null);

        // Find views in the dialog layout
        EditText editTextName = dialogView.findViewById(R.id.editTextText3);
        EditText editTextPhone = dialogView.findViewById(R.id.editTextPhone);
        Button addButton = dialogView.findViewById(R.id.button5);

        // Create the AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        AlertDialog dialog = builder.create();

        // Set a click listener for the "Add" button
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the "Add" button click
                String doctorName = editTextName.getText().toString();
                String phoneNumber = editTextPhone.getText().toString();

                // Add a TextView dynamically to your layout with the entered details
                addDynamicTextView(doctorName, phoneNumber);

                // Dismiss the dialog
                dialog.dismiss();
            }
        });

        // Show the dialog
        dialog.show();
    }

    private void showAddEmergencyContactDialog() {
        // Inflate the emergency contact dialog layout
        View dialogView = LayoutInflater.from(this).inflate(R.layout.emergency_contacts, null);

        // Find views in the dialog layout
        EditText editTextName = dialogView.findViewById(R.id.editTextName);
        EditText editTextRelation = dialogView.findViewById(R.id.editTextRelation);
        EditText editTextEmergencyPhone = dialogView.findViewById(R.id.editTextEmergencyPhone);
        Button addButtonEmergencyContact = dialogView.findViewById(R.id.buttonAddEmergencyContact);

        // Create the AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        AlertDialog dialog = builder.create();

        // Set a click listener for the "Add" button
        addButtonEmergencyContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the "Add" button click
                String name = editTextName.getText().toString();
                String relation = editTextRelation.getText().toString();
                String emergencyPhone = editTextEmergencyPhone.getText().toString();

                // Add a TextView dynamically to your layout with the entered emergency contact details
                addDynamicEmergencyContactTextView(name, relation, emergencyPhone);

                // Dismiss the dialog
                dialog.dismiss();
            }
        });

        // Show the dialog
        dialog.show();
    }

    private void addDynamicEmergencyContactTextView(String name, String relation, String emergencyPhone) {
        // Create a new TextView
        TextView dynamicTextView = new TextView(this);
        dynamicTextView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        dynamicTextView.setText("Name: " + name + "\nRelation: " + relation + "\nPhone: " + emergencyPhone);

        // Add the TextView to your addEmergencyContact LinearLayout
        addEmergencyContact.addView(dynamicTextView);
    }

    private void addDynamicTextView(String doctorName, String phoneNumber) {
        // Create a new TextView
        TextView dynamicTextView = new TextView(this);
        dynamicTextView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        dynamicTextView.setText("Doctor Name: " + doctorName + "\nPhone Number: " + phoneNumber);

        // Add the TextView to your dynamicContainer LinearLayout
        dynamicContainer.addView(dynamicTextView);
    }

    public void onLogout(View view) {
        auth.signOut();
        Toast.makeText(this, "Logout Successful",
                Toast.LENGTH_LONG).show();
        startActivity(new Intent(this, login.class));
    }


}