package com.example.baymax;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;

public class register extends AppCompatActivity {

    EditText email, pass, cpass,username;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = (EditText) findViewById(R.id.editTextTextEmailAddress);
        pass = (EditText) findViewById(R.id.editTextTextPassword);
        cpass = (EditText) findViewById(R.id.editTextTextPassword2);
        username = (EditText)findViewById(R.id.editTextText);
        auth = FirebaseAuth.getInstance();


        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public void onAlreadyRegistered(View view) {
        Intent i = new Intent(this, login.class);
        startActivity(i);
        finish();
    }

    public void onSubmit(View view) {
        String emailInput = email.getText().toString().trim();
        String password = pass.getText().toString().trim();
        String Username = username.getText().toString().trim();

        // Validate username
        if (TextUtils.isEmpty(Username)) {
            Toast.makeText(getApplicationContext(), "Enter a username!", Toast.LENGTH_SHORT).show();
            return;
        } if (Username.length() < 4) {
            Toast.makeText(getApplicationContext(), "Username must be at least 4 characters!", Toast.LENGTH_SHORT).show();
            return;
        } if (Character.isDigit(Username.charAt(0))) {
            Toast.makeText(getApplicationContext(), "Username cannot begin with a number!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(emailInput)) {
            Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            Toast.makeText(getApplicationContext(), "Enter valid email", Toast.LENGTH_LONG).show();
        }

            if (TextUtils.isEmpty(password)) {
                Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (password.length() < 6) {
                Toast.makeText(getApplicationContext(), "Password must be more than 6 characters!", Toast.LENGTH_SHORT).show();
                return;
            }

        // Check if user with the same email already exists
        auth.fetchSignInMethodsForEmail(emailInput).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
            @Override
            public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                if (task.isSuccessful()) {
                    SignInMethodQueryResult result = task.getResult();
                    if (result != null && result.getSignInMethods() != null && result.getSignInMethods().size() > 0) {
                        // User with the same email already exists
                        Toast.makeText(getApplicationContext(), "User with this email already exists!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Handle the case when the task to check email fails
                    Toast.makeText(getApplicationContext(), "Error checking email. Please try again.", Toast.LENGTH_SHORT).show();
                }
            }
        });



            //Create User
            auth.createUserWithEmailAndPassword(emailInput, password).addOnCompleteListener(register.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    Toast.makeText(register.this, "Registration Complete" + task.isSuccessful(), Toast.LENGTH_SHORT).show();

                    if (!task.isSuccessful()) {
                        Toast.makeText(register.this, "Registration Failed!" + task.getException(), Toast.LENGTH_LONG).show();
                        Log.e("MyTag", task.getException().toString());
                    } else {
                        startActivity(new Intent(register.this, login.class));
                        finish();
                    }
                }
            });




    }
}