package com.example.zenmitmusic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.Objects;

public class Search extends AppCompatActivity {
    ArrayList<Music> allSongs = MusicStorage.getAllSongs();
    ArrayList<Music> filteredList = new ArrayList<>();
    BottomNavigationView bottomNavigationView;
    private AdapterT.RecyclerViewClickListener listener;
    AdapterT adapter;
    private RecyclerView searchList;
    FloatingActionButton fab;
    boolean doublePress = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Search");

        setBottomNavigationView();
        setFab();
        setAdapter();
        EditText searchInput = findViewById(R.id.searchInput);
        searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });
    }
    @Override
    public void onBackPressed() {
        // double tap to exit
        if (doublePress) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            return;
        }
        doublePress = true;
        Toast.makeText(this, "Tap again to EXIT", Toast.LENGTH_SHORT).show();
        // change back to false after 2 seconds
        new Handler().postDelayed(() -> doublePress=false, 2000);
    }

    private void setBottomNavigationView(){
        bottomNavigationView=findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.search);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    overridePendingTransition(0,0);
                    return true;

                case R.id.search:
                    return true;


                case R.id.settings:
                    startActivity(new Intent(getApplicationContext(),Settings.class));
                    overridePendingTransition(0,0);
                    return true;

            }
            return false;
        });

    }



    private void setFab() {
        // initialise the floating action button
        fab = findViewById(R.id.fab);
        if (isServiceRunning(MusicSource.class)) {
            fab.show();
        } else {
            fab.hide();
        }
        fab.setOnClickListener(view -> {
            if (isServiceRunning(MusicSource.class)) {
                Intent intent = new Intent(Search.this, MusicPlay.class);
                startActivity(intent);
            } else {
                Toast.makeText(Search.this, "Nothing is being played!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void setAdapter() {
        // set the recyclerview adapter

        LinearLayoutManager layoutManager;
        setOnClickListener();
        // initialise the recyclerview
        RecyclerView searchList = findViewById(R.id.searchList);

        // bind filteredList to recyclerview
        adapter = new AdapterT(filteredList, listener);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        searchList.setLayoutManager(layoutManager);
        searchList.setAdapter(adapter);
    }

    private void setOnClickListener() {
        //  On-click listener for the recyclerview
        listener = (v, position) -> {
            Intent intent = new Intent(getApplicationContext(), MusicSource.class);

            Bundle bundle = new Bundle();
            bundle.putSerializable("songList", filteredList);
            intent.putExtras(bundle);

            intent.putExtra("songName", filteredList.get(position).getTitle());
            intent.putExtra("artisteID", filteredList.get(position).getArtiste());
            intent.putExtra("coverArt",filteredList.get(position).getImageIcon());
            intent.putExtra("fileLink", filteredList.get(position).getFileLink());
            intent.putExtra("songId",filteredList.get(position).getId());
            startService(intent);

            if (!fab.isShown()) {
                fab.show();
            }
        };
    }

    private void filter(String text) {
        // filter songs based on text input
        filteredList.clear();

        if (text.isEmpty()) {
            adapter.filterList(filteredList);
        }
        else {
            for(Music song : allSongs) {
                if (song.getTitle().toLowerCase().contains(text.toLowerCase())) {
                    filteredList.add(song);
                }
                else if (song.getArtiste().toLowerCase().contains(text.toLowerCase())) {
                    filteredList.add(song);
                }
            }
            adapter.filterList(filteredList);
        }
    }

    private boolean isServiceRunning(Class<?> serviceClass) {
        // check if service is running
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}