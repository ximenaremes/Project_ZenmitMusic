package com.example.zenmitmusic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class Playlist extends AppCompatActivity implements Delete.DeleteDialogListener {
    BottomNavigationView bottomNavigationView;
    private RecyclerView trackList;

    private final ArrayList<Music> songList = new ArrayList<>();
    private final Boolean isLibraryActivity = true;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference dbRef = database.getReference();
    DatabaseReference userRef = dbRef.child(user.getUid());
    Delete delete = new Delete();

    AdapterT adapter;
    private AdapterT.RecyclerViewClickListener listener;

    FloatingActionButton fab;
    boolean doublePress = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);
        bottomNavigationView=findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    return true;

                case R.id.search:
                    startActivity(new Intent(getApplicationContext(),Search.class));
                    overridePendingTransition(0,0);
                    return true;


                case R.id.settings:
                    startActivity(new Intent(getApplicationContext(),Settings.class));
                    overridePendingTransition(0,0);
                    return true;
            }
            return false;
        });

        Objects.requireNonNull(getSupportActionBar()).setTitle("Playlist");
        setFab();
        setAdapter();
        prepareLibrarySongs();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // Display the delete button in the toolbar
        getMenuInflater().inflate(R.menu.item_delete, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        // On-click listener for settings button
        if (item.getItemId() == R.id.delete) {
            delete.show(getSupportFragmentManager(), "Delete Dialog");
            return true;
        }
        return super.onOptionsItemSelected(item);
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
                Intent intent = new Intent(Playlist.this, MusicPlay.class);
                intent.putExtra("isLibraryActivity", isLibraryActivity);
                startActivity(intent);
            } else {
                Toast.makeText(Playlist.this, "Nothing is being played!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setAdapter() {
        // set the recyclerview adapter

        LinearLayoutManager layoutManager;
        setOnClickListener();
        // initialise the recyclerview
       trackList = findViewById(R.id.trackList);

        // bind songList to recyclerview
        adapter = new AdapterT(songList, listener);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        trackList.setLayoutManager(layoutManager);
        trackList.setAdapter(adapter);
    }

    private void setOnClickListener() {
        //  On-click listener for the recyclerview
        listener = (v, position) -> {
            Intent intent = new Intent(getApplicationContext(), MusicSource.class);

            Bundle bundle = new Bundle();
            bundle.putSerializable("songList", songList);
            intent.putExtras(bundle);

            intent.putExtra("songName", songList.get(position).getTitle());
            intent.putExtra("artisteID", songList.get(position).getArtiste());
            intent.putExtra("coverArt",songList.get(position).getImageIcon());
            intent.putExtra("fileLink", songList.get(position).getFileLink());
            intent.putExtra("songId",songList.get(position).getId());
            startService(intent);

            if (!fab.isShown()) {
                fab.show();
            }
        };
    }

    private void prepareLibrarySongs() {
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String[] valueArray = new String[5];

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Music song;
                    // store the child node value in 'value'
                    String value = Objects.requireNonNull(ds.getValue()).toString();
                    // the values are separated by commas
                    if (value!=null){
                    valueArray = value.split(",");}
                    String songId = valueArray[0];
                    String title = valueArray[1];
                    String artiste = valueArray[2];
                    String fileLink = valueArray[3];
                    String imageIcon = valueArray[4];
                    int image = Integer.parseInt(imageIcon);
                    song = new Music(songId, title, artiste, fileLink, image);
                    boolean isAdded = false;

                    for (int i = 0; i < songList.size(); i++){
                        if (song.getId().equals(songList.get(i).getId())) {
                            isAdded = true;
                            break;
                        }
                    }
                    if (isAdded==false)
                        songList.add(song);
                }
                adapter.filterList(songList);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
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
    @Override
    public void onDialogPositiveClick() {
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                songList.clear(); // clear the arraylist
                userRef.removeValue(); // clear the node in the database
                adapter.filterList(songList); // update the recyclerview
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onDialogNegativeClick() {

    }
}