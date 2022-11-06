package com.example.zenmitmusic;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class Settings extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    ImageView image_back;
    Button btnLogout;
    FirebaseAuth mAuth;
    ImageView imageArr1;
    ImageView imageAbout;
    TextView textInfo;
    TextView textProfile;
    ImageView imageinfo;
    ImageView imageProfile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
       // setBottomNavigationView();

        btnLogout=findViewById(R.id.btnLogout);
        mAuth=FirebaseAuth.getInstance();

        btnLogout.setOnClickListener(view -> {
            mAuth.signOut();
            startActivity(new Intent(Settings.this, Login.class));
        });
        image_back=findViewById(R.id.image_back);
        imageArr1=findViewById(R.id.imageArr1);
        imageAbout=findViewById(R.id.imageAbout);
        textInfo=findViewById(R.id.textInfo);
        textProfile=findViewById(R.id.textProfile);
        imageinfo=findViewById(R.id.imageinfo);
        imageProfile=findViewById(R.id.imageProfile);

        image_back.setOnClickListener(view -> startActivity(new Intent(Settings.this, MainActivity.class)));
        imageArr1.setOnClickListener(view -> startActivity(new Intent(Settings.this, Profile.class)));
        textProfile.setOnClickListener(view -> startActivity(new Intent(Settings.this, Profile.class)));
        imageProfile.setOnClickListener(view -> startActivity(new Intent(Settings.this, Profile.class)));

        imageAbout.setOnClickListener(view -> startActivity(new Intent(Settings.this, AboutUs.class)));
        textInfo.setOnClickListener(view -> startActivity(new Intent(Settings.this, AboutUs.class)));
        imageinfo.setOnClickListener(view -> startActivity(new Intent(Settings.this, AboutUs.class)));

    }
    /*@Override
    protected void onStart(){
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user==null){
            startActivity(new Intent(Settings.this,  MainActivity.class));
        }
    }*/
   /* private void setBottomNavigationView(){
        bottomNavigationView=findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.settings);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    overridePendingTransition(0,0);
                    return true;

                case R.id.search:
                    startActivity(new Intent(getApplicationContext(),Search.class));
                    overridePendingTransition(0,0);
                    return true;

                case R.id.favorite:
                    startActivity(new Intent(getApplicationContext(),Favorites.class));
                    overridePendingTransition(0,0);
                    return true;

                case R.id.settings:
                    return true;

            }
            return false;
        });

    }*/

}