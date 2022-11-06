package com.example.zenmitmusic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class MusicPlay extends AppCompatActivity {

MediaPlayer player;
    ImageButton paused = null;
    ImageButton repeatButton = null;
    ImageButton shuffleButton = null;
    TextView txtCurrentTime = null;

    private SeekBar seekBar = null;
    private final Handler handler = new Handler();
    ArrayList<Music> songList = new ArrayList<>();


    Boolean isAdded = false;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference songRef, dbRef, userRef;
    FirebaseUser user;
    ImageButton addToLibraryBtn;

    MusicSource mService;
    boolean mBound = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_play);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        seekBar = findViewById(R.id.seekBar);
        txtCurrentTime = findViewById(R.id.txtCurrentTime);

        addToLibraryBtn  = findViewById(R.id.addToLibraryButton);

        user = FirebaseAuth.getInstance().getCurrentUser();
        assert user != null;
        String userId = user.getUid();
        dbRef = database.getReference(); // reference the whole database
        userRef = database.getReference(userId);

    }


    private String milliSecondsToTimer(long milliseconds) {
        String finalTimerString = "";
        String secondsString;

        // Convert total duration into time
        int seconds = (int) ((milliseconds % (1000 * 60 * 60)) % (1000 * 60) / 1000);

        // Prepending 0 to seconds if it is one digit
        if (seconds < 10) {
            secondsString = "0" + seconds;
        } else {
            secondsString = "" + seconds;
        }
        finalTimerString = finalTimerString + "0" + ":" + secondsString;

        // return timer string
        return finalTimerString;
    }


    public void playSong(View view){
        MediaPlayer media= new MediaPlayer();
        try{
            media.setDataSource("https://firebasestorage.googleapis.com/v0/b/zenmit-music-d5531.appspot.com/o/Music%2FAdrian_Sina_feat_Cojo_Zile_Bune_Zile_Rele.mp3?alt=media&token=435f895b-5582-4524-8172-f0adfb401690");
            media.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();
                }
            });
            media.prepare();
        }catch (IOException e){
            e.printStackTrace();
        }
    }



}