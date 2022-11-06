package com.example.zenmitmusic;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;


public class Reset extends AppCompatActivity {
    TextView emailEdit;
    Button btnResetP;
    TextView textLogin;
    FirebaseAuth mAuth;
    boolean doublePress = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);
        emailEdit=findViewById(R.id.emailEdit);
        btnResetP=findViewById(R.id.btnResetP);
        textLogin=findViewById(R.id.textLogin);
        mAuth=FirebaseAuth.getInstance();
        textLogin.setOnClickListener(view -> startActivity(new Intent(Reset.this, Login.class)));

    }

    private void resetPassword() {
        String email = emailEdit.getText().toString().trim();
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailEdit.setError("Please Enter Valid Email");
            emailEdit.requestFocus();
            return;

        }
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                Toast.makeText(getApplicationContext(), "Please check your email!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Reset.this,Login.class );
                startActivity(intent);

            }
            else{
                Toast.makeText(getApplicationContext(), "Failed to Reset Password!", Toast.LENGTH_LONG).show();

            }

        });
    }


    public void ResetPasswordBtn(android.view.View view) {
        resetPassword();
    }

    @Override
    public void onBackPressed() {
        // double tap to exit
        if (doublePress) {
            super.onBackPressed();
            return;
        }
        doublePress = true;
        Toast.makeText(this, "Tap again to EXIT", Toast.LENGTH_SHORT).show();
        // change back to false after 2 seconds
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doublePress=false;
            }
        }, 2000);
    }
}