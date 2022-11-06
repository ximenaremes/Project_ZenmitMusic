package com.example.zenmitmusic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class SignUp extends AppCompatActivity {
    private TextView textName;
    private TextView phone;
    private TextView emailAdd;
    private TextView passAdd;
    private FirebaseDatabase database;
    private DatabaseReference mDdatabase;
    private FirebaseAuth mAuth;
    private FirebaseFirestore fStore;
    //private static final java.lang.String USER = "user";
    //private User user;
    private final String TAG =this.getClass().getName().toUpperCase();
    boolean doublePress = false;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        textName = findViewById(R.id.textName);
        phone = findViewById(R.id.phone);
        emailAdd = findViewById(R.id.emailAdd);
        passAdd = findViewById(R.id.passAdd);
        Button btnSignUp = findViewById(R.id.btnSignUp);
        mAuth = FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();


        btnSignUp.setOnClickListener(view -> {
            database = FirebaseDatabase.getInstance();
            mDdatabase = database.getReference("USER");
            mAuth = FirebaseAuth.getInstance();

            String name = textName.getText().toString();
            String phonee = phone.getText().toString();
            String email = emailAdd.getText().toString();
            String password = passAdd.getText().toString();


            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(phonee) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                Toast.makeText(SignUp.this, "All fields are required", Toast.LENGTH_SHORT).show();

            }
            if (TextUtils.isEmpty(name)) {
                textName.setError("Name is required!");
                textName.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(phonee)) {
                phone.setError("Phone is required!");
                phone.requestFocus();
                return;
            }
            if (phonee.length() < 10 ) {
                phone.setError("Minimum length of phone is 10.");
                phone.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(email)) {
                emailAdd.setError("Email cannot be empty!");
                emailAdd.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(password)) {
                passAdd.setError("Password cannot be empty");
                passAdd.requestFocus();
                return;
            }
            if (password.length() < 6 ) {
                passAdd.setError("Minimum length of password is 6.");
                passAdd.requestFocus();
                return;
            }

            User user = new User(name, phonee, email, password);
            String keyId= mDdatabase.push().getKey();
            assert keyId != null;
            mDdatabase.child(keyId).setValue(user);
            createUser(name, phonee, email, password);
        });
    }

    public void createUser(String name, String phonee, String email,String password) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(SignUp.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                userID= mAuth.getCurrentUser().getUid();
                DocumentReference documentReference= fStore.collection("USER").document(userID);
                Map<String, Object>user= new HashMap<>();
                user.put("name", name);
                user.put("phone", phonee);
                user.put("email", email);
                user.put("password", password);
                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.v(TAG, "onSuccess: User profile is created for "+userID);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.v(TAG, "onFailure: "+ e.toString());

                    }
                });
                startActivity(new Intent(SignUp.this, Login.class));

            } else {

                Toast.makeText(SignUp.this, "Registration Erorr:"+ Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

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





