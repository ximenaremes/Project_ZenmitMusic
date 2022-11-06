package com.example.zenmitmusic;
import android.app.Service;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class MusicSource extends Service {

    int img = 0;
    private String url = "";
    private String artisteName = "";
    private String songId = "";
    private String songTitle = "";
    private String fileLink = "";
    ArrayList<Music> songList;
    ArrayList<Music> shuffledList = new ArrayList<>();

    boolean isShuffle = false;

    int musicPosition = 0;

    private final IBinder binder = new LocalBinder();

    private static MediaPlayer player;

    @Override
    public void onCreate() {
        player = new MediaPlayer();
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Toast.makeText(this, "Service started", Toast.LENGTH_LONG).show();
        fileLink = intent.getStringExtra("fileLink");
        url = "https://firebasestorage.googleapis.com/v0/b/zenmit-music-d5531.appspot.com/o/Music%" + fileLink;
        img = intent.getIntExtra("coverArt",0);
        songList = (ArrayList<Music>) intent.getSerializableExtra("songList");
        songTitle = intent.getStringExtra("songName");
        songId = intent.getStringExtra("songId");
        artisteName = intent.getStringExtra("artisteID");
        preparePlayer();
        player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer player) {
                player.start();
            }
        });


        player.setOnCompletionListener(mp -> {
            if (!player.isLooping())
                playNext();
        });
        Toast.makeText(this, "Now playing: " + songTitle + " by " + artisteName, Toast.LENGTH_SHORT).show();
        return Service.START_NOT_STICKY;
    }
    @Override
    public void onDestroy() {
        player.stop();
        player.release();
        player = null;
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return binder;
    }

    public class LocalBinder extends Binder {
        MusicSource getService() {
            // Return this instance of LocalService so clients can call public methods
            if (isShuffle==false) {
                // shuffle the CURRENT list if rebound to another song
                shuffleSongs();
            }
            return MusicSource.this;
        }
    }

    public void preparePlayer() {
        player.reset();

        try {
            player.setAudioAttributes(
                    new AudioAttributes
                            .Builder()
                            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                            .setUsage(AudioAttributes.USAGE_MEDIA)
                            .build());
            player.setDataSource(url);
            player.prepareAsync();
        } catch (IllegalArgumentException | IOException e) {
            e.printStackTrace();
        }
    }

    public void playNext() {
        Music nextSong = getNextSong(songId);
        if (nextSong != null) {
            songId = nextSong.getId();
            songTitle = nextSong.getTitle();
            artisteName = nextSong.getArtiste();
            fileLink = nextSong.getFileLink();
            url = "https://firebasestorage.googleapis.com/v0/b/zenmit-music-d5531.appspot.com/o/Music%" + nextSong.getFileLink();
            img = nextSong.getImageIcon();
        }
        preparePlayer();
        player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer player) {
                player.start();
            }
        });
    }

    public Music getNextSong(String currentSongId) {
        Music song = null;
        if (isShuffle==true) {
            if (shuffledList == null) {
                return song;
            }

            for(int i = 0; i < shuffledList.size(); i++) {
                // if last song in list, play the first song
                if (shuffledList.get(shuffledList.size() - 1).getId().equals(currentSongId)) {
                    song = shuffledList.get(0);
                    break;
                }
                String tempSongId = shuffledList.get(i).getId();
                if (tempSongId.equals(currentSongId)) {
                    // find the current song in shuffled arraylist and return the next
                    song = shuffledList.get(i+1);
                    break;
                }
            }
        }
        else {
            if (songList == null) {
                return song;
            }

            for(int i = 0; i < songList.size(); i++) {
                // if last song in list, play the first song
                if (songList.get(songList.size() - 1).getId().equals(currentSongId)) {
                    song = songList.get(0);
                    break;
                }
                String tempSongId = songList.get(i).getId();
                if (tempSongId.equals(currentSongId)) {
                    // find the current song in arraylist and return the next
                    song = songList.get(i+1);
                    break;
                }
            }
        }
        return song;
    }

    public void playPrev() {
       Music prevSong = getPrevSong(songId);
        if (prevSong != null) {
            songId = prevSong.getId();
            songTitle = prevSong.getTitle();
            artisteName = prevSong.getArtiste();
            fileLink = prevSong.getFileLink();
            url = "https://firebasestorage.googleapis.com/v0/b/zenmit-music-d5531.appspot.com/o/Music%" + prevSong.getFileLink();
            img = prevSong.getImageIcon();
        }
        preparePlayer();
        player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer player) {
                player.start();
            }
        });
    }

    public Music getPrevSong(String currentSongId) {
        Music song = null;
        if (isShuffle==false) {
            if (songList == null) {
                return song;
            }

            for (int i = 0; i < songList.size(); i++) {
                // if first song in list, play the last song
                if (songList.get(0).getId().equals(currentSongId)) {
                    song = songList.get(songList.size() - 1);
                    break;
                }
                String tempSongId = songList.get(i).getId();
                // find the current song in arraylist and return the previous
                if (tempSongId.equals(currentSongId)) {
                    song = songList.get(i-1);
                    break;
                }
            }
        }
        else if (isShuffle == true){
            if (shuffledList == null) {
                return song;
            }

            for (int i = 0; i < shuffledList.size(); i++) {
                if (shuffledList.get(0).getId().equals(currentSongId)) {
                    song = shuffledList.get(shuffledList.size() - 1);
                    break;
                }
                String tempSongId = shuffledList.get(i).getId();
                if (tempSongId.equals(currentSongId)) {
                    song = shuffledList.get(i - 1);
                    break;
                }
            }
        }
        return song;
    }

    public void shuffleSongs() {
        isShuffle = true;
        shuffledList.clear();
        shuffledList.addAll(songList);
        Collections.shuffle(shuffledList);
    }
    public void unshuffleSongs() {
        isShuffle = false;
    }

    public void loopSong(boolean isLoop) {
        if (isLoop == true)
            player.setLooping(true);
        else
            player.setLooping(false);
    }

    public void playMusic(){
        player.start();
    }
    public void pauseMusic(){
        musicPosition = player.getCurrentPosition();
        player.pause();
    }

    // methods to get MediaPlayer values or control
    public boolean isMusicPlaying() {
        return player.isPlaying();
    }
    public int getMusicPosition(){
        return player.getCurrentPosition();
    }
    public void seekToPos (int pos){
        player.seekTo(pos);
    }
    public int getMusicDuration() {
        return player.getDuration();
    }
    public boolean getLoopState() {
        return player.isLooping();
    }
    public boolean getShuffleState() {
        return isShuffle;
    }

    // methods to get Song object values
    public int getCoverArt() {
        return img;
    }
    public String getArtiste() {
        return artisteName;
    }
    public String getSongTitle() {
        return songTitle;
    }
    public String getSongId() {
        return songId;
    }
    public String getFileLink() {
        return fileLink;
    }
    public ArrayList<Music> getSongList() {
        return songList;
    }
}
