package com.example.zenmitmusic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class AboutUs extends AppCompatActivity {
    ImageView image_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        image_back=findViewById(R.id.image_back);
        image_back.setOnClickListener(view -> startActivity(new Intent(AboutUs.this, Settings.class)));
    }
}