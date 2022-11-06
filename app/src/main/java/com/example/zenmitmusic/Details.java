package com.example.zenmitmusic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Details extends AppCompatActivity {

    ArrayList<Music> songList = new ArrayList<>();
    private RecyclerView trackList;
    private AdapterT.RecyclerViewClickListener listener;

    FloatingActionButton fab;
    boolean doublePress = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setFab();
        retrieveData();
        setAdapter();
        getSupportActionBar().setTitle(songList.get(0).getArtiste());
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
                doublePress = false;
            }
        }, 2000);
    }

    private void setFab() {
        // initialise the floating action button
        fab = findViewById(R.id.fab);
        if (isServiceRunning(MusicSource.class)) {
            fab.show();
        } else {
            fab.hide();
        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isServiceRunning(MusicSource.class)) {
                    Intent intent = new Intent(Details.this, MusicPlay.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(Details.this, "Nothing is being played!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setAdapter() {
        setOnClickListener();
        trackList = findViewById(R.id.trackList);
        AdapterT adapter = new AdapterT(songList, listener);
        LinearLayoutManager layoutManager;
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        trackList.setLayoutManager(layoutManager);
        trackList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void setOnClickListener() {
        listener = new AdapterT.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(getApplicationContext(), MusicSource.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("songList", songList);
                intent.putExtras(bundle);

                intent.putExtra("songName", songList.get(position).getTitle());
                intent.putExtra("artisteID", songList.get(position).getArtiste());
                intent.putExtra("coverArt", songList.get(position).getImageIcon());
                intent.putExtra("fileLink", songList.get(position).getFileLink());
                intent.putExtra("songId", songList.get(position).getId());
                startService(intent);

                if (!fab.isShown()) {
                    fab.show();
                }
            }
        };
    }

    private void retrieveData() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            songList = (ArrayList<Music>) extras.getSerializable("songList");
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // for the back button in the title bar
        Intent intent = new Intent(Details.this, MainActivity.class);
        startActivity(intent);
        finish();
        return true;
    }

    private boolean isServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

}