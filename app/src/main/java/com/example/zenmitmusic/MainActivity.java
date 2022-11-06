package com.example.zenmitmusic;

import androidx.annotation.NonNull;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    ImageView image1,image2, image3, image4;
    RecyclerView topTracks, topArtistes, otherSongs, recyclerView;
    AdapterH songAdapter, artisteAdapter;
    AdapterS otherSongsAdapter;
    AdapterH.RecyclerViewClickListener songListener, artisteListener;
    AdapterS.RecyclerViewClickListener songsListener;
    ArrayList<Music> MusicList1 = MusicStorage.getTopSongs();
    ArrayList<Music> artisteList = MusicStorage.getTopArtistes();
    ArrayList<Music> MusicList2;
    FirebaseUser user;
    String name;
    FloatingActionButton play;
    private JSONObject testObj;
    private JSONArray testArr;
    boolean doublePress = false;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setBottomNavigationView();
        setPlayMusic();
        setAdapter();
        MusicList2= new ArrayList<>();
        user = FirebaseAuth.getInstance().getCurrentUser();
        getSupportActionBar().setTitle("Welcome to Zenmit Music!");
            image1=findViewById(R.id.image1);
             image2=findViewById(R.id.image2);
             image3=findViewById(R.id.image3);
             image4=findViewById(R.id.image4);

        image1.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, MusicPlay.class)));
        image2.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, MusicPlay.class)));
        image3.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, MusicPlay.class)));
        image4.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, MusicPlay.class)));
        }

    private void setPlayMusic() {
        // initialise the floating action button
        play = findViewById(R.id.play);
        if (isServiceRunning(MusicSource.class)) {
            play.show();
        } else {
            play.hide();
        }
        play.setOnClickListener(view -> {
            if (isServiceRunning(MusicSource.class)) {
                Intent intent = new Intent(MainActivity.this, MusicPlay.class);
                startActivity(intent);
            } else {
                Toast.makeText(MainActivity.this, "Nothing is being played!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setBottomNavigationView(){
        bottomNavigationView=findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
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
            }
        });

    }

    private void setAdapter() {
        // set the recyclerview adapters

        LinearLayoutManager artisteLayoutManager, songsLayoutManager;
        setOnClickListener();
        // initialise the recyclerview
        topArtistes = findViewById(R.id.songs);
        topTracks = findViewById(R.id.songs);
        otherSongs  = findViewById(R.id.songs);
        recyclerView = findViewById(R.id.songs);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // bind songList to recyclerview
        songAdapter = new AdapterH(MusicList1, true, songListener);
        LinearLayoutManager songLayoutManager = new LinearLayoutManager(this);
        songLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        topTracks.setAdapter(songAdapter);
        topTracks.setLayoutManager(songLayoutManager);

        // bind artisteList to recyclerview
        artisteAdapter = new AdapterH(artisteList, false, artisteListener);
        artisteLayoutManager = new LinearLayoutManager(this);
        artisteLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        topArtistes.setAdapter(artisteAdapter);
        topArtistes.setLayoutManager(artisteLayoutManager);


        songsLayoutManager = new LinearLayoutManager(this);
        songsLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        otherSongs.setLayoutManager(songsLayoutManager);
        fetchSongs();

    }
    private void fetchSongs() {
        String URL = "https://api.deezer.com/user/2529/playlists";
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                URL,
                null,
                response -> {

                    try {
                        //testObj = (JSONObject) response.get("type");
                        //Log.i("the song is:", String.valueOf(testObj));
                        testArr = (JSONArray) response.optJSONArray("data");
                        Log.i("altceva:", String.valueOf(testArr));

                        for (int i = 0; i < testArr.length(); i++) {
                            JSONObject jsonObject = testArr.getJSONObject(i);

                           MusicList2.add(new Music(jsonObject.optString("id"), jsonObject.optString("title"), jsonObject.optString(""), jsonObject.optString("link"), jsonObject.optString("picture")));
                        }
                        for(int i=0;i< MusicList2.size();i++){
                            Log.i("ce vrei tu:",  MusicList2.get(i).toString());
                        }
                        otherSongsAdapter = new AdapterS( MusicList2,songsListener);

                        otherSongs.setAdapter(otherSongsAdapter);



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    //  Log.i("the song is:", String.valueOf(response));


                    //    } catch (JSONException e) {
                    //  e.printStackTrace();
                    // }
                },
                error -> Log.i("the res is error:", error.toString())
        );
        requestQueue.add(jsonObjectRequest);
    }

    private void setOnClickListener() {
        //  On-click listeners for the two recyclerviews
        songListener = (v, position) -> {
            Intent intent = new Intent(getApplicationContext(), MusicSource.class);

            Bundle bundle = new Bundle();
            bundle.putSerializable("songList", MusicList1);
            intent.putExtras(bundle);

            intent.putExtra("songName", MusicList1.get(position).getTitle());
            intent.putExtra("artisteID", MusicList1.get(position).getArtiste());
            intent.putExtra("coverArt", MusicList1.get(position).getImageIcon());
            intent.putExtra("fileLink", MusicList1.get(position).getFileLink());
            intent.putExtra("songId", MusicList1.get(position).getId());
            startService(intent);

            if (!play.isShown()) {
                play.show();
            }
        };

        artisteListener = (v, position) -> {
            Intent intent = new Intent(MainActivity.this, MusicPlay.class);

            Bundle bundle = new Bundle();
            bundle.putSerializable("songList", artisteList.get(position).getSongList());
            intent.putExtras(bundle);

            startActivity(intent);
        };

        songsListener = (v, position) -> {
            Intent intent = new Intent(getApplicationContext(), MusicSource.class);

            intent.putExtra("songName",  MusicList2.get(position).getTitle());
            intent.putExtra("artisteID",  MusicList2.get(position).getArtiste());
            intent.putExtra("coverArt", MusicList2.get(position).getImageIcon2());
            intent.putExtra("fileLink",  MusicList2.get(position).getFileLink());
            intent.putExtra("songId",  MusicList2.get(position).getId());
            startService(intent);

            if (!play.isShown()) {
                play.show();
            }
        };

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
}