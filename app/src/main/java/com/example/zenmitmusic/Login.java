package com.example.zenmitmusic;



import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import android.util.Patterns;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;


public class Login extends AppCompatActivity {
    TextView emailAdd;
    TextView passAdd;
    Button btnSignIn;
    TextView textReset;
    TextView textvSignUp;
    FirebaseAuth mAuth;
    FirebaseUser user;
    boolean doublePress = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailAdd= findViewById(R.id.emailAdd);
        passAdd= findViewById(R.id.passAdd);
        btnSignIn= findViewById(R.id.btnSignIn);
        textReset = findViewById(R.id.textReset);
        textvSignUp = findViewById(R.id.textvSignUp);

        mAuth=FirebaseAuth.getInstance();

        btnSignIn.setOnClickListener(view -> loginUser());
        textReset.setOnClickListener(view -> startActivity(new Intent(Login.this, Reset.class)));

        textvSignUp.setOnClickListener(view -> startActivity(new Intent(Login.this, SignUp.class)));
    }
    @Override
    public void onBackPressed() {
        if (doublePress) {
            super.onBackPressed();
            return;
        }
        doublePress = true;
        Toast.makeText(this, "Press again to EXIT", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(() -> doublePress=false, 2000);
    }
    public void  loginUser() {
        String email = emailAdd.getText().toString();
        String password = passAdd.getText().toString();

        if (email.isEmpty()) {
            emailAdd.setError("Email is required");
            emailAdd.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailAdd.setError("Please enter a valid email address.");
            emailAdd.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            passAdd.setError("Password is required");
            passAdd.requestFocus();
            return;
        }

        if (password.length() < 6) {
            passAdd.setError("Minimum length of password is 6.");
            passAdd.requestFocus();
            return;
        }
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {

            if (task.isSuccessful()) {
                user = mAuth.getCurrentUser();
                assert user != null;
                if (user.isEmailVerified()) {
                    Toast.makeText(getApplicationContext(), "Please verify your email!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "User logged in successfully", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            } else {
                Toast.makeText(getApplicationContext(), "Log in Erorr:"+ Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        );
    }
}








