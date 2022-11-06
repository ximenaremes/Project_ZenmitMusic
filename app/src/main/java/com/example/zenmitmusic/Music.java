package com.example.zenmitmusic;

import java.io.Serializable;
import java.util.ArrayList;

public class Music implements Serializable {
    private String id;
    private int imageIcon;

    public String getImageIcon2() {
        return imageIcon2;
    }

    public void setImageIcon2(String imageIcon2) {
        this.imageIcon2 = imageIcon2;
    }

    private String imageIcon2;
    private String title;

    public Music(String id, String title, String artiste, String fileLink,String imageIcon2) {
        this.id = id;
        this.imageIcon2 = imageIcon2;
        this.title = title;
        this.artiste = artiste;
        this.fileLink = fileLink;
    }

    private String artiste;
    private String fileLink;
    private ArrayList<Music> songList;

    public Music(String id, String title, String artiste, String fileLink, int imageIcon) {
        this.id = id;
        this.imageIcon = imageIcon;
        this.title = title;
        this.artiste = artiste;
        this.fileLink = fileLink;
    }

    @Override
    public String toString() {
        return "Music{" +
                "id='" + id + '\'' +
                ", imageIcon2='" + imageIcon2 + '\'' +
                ", title='" + title + '\'' +
                ", artiste='" + artiste + '\'' +
                ", fileLink='" + fileLink + '\'' +
                '}';
    }

    String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getFileLink() { return fileLink; }
    public void setFileLink(String fileLink) { this.fileLink = fileLink; }


    int getImageIcon() {
        return imageIcon;
    }
    public void setImageIcon(int imageIcon) { this.imageIcon = imageIcon; }

    String getTitle() {
        return title;
    }
    public void setTitle(String title) { this.title = title; }

    String getArtiste() {
        return artiste;
    }
    public void setArtiste(String artiste) { this.artiste = artiste; }

    public ArrayList<Music> getSongList() {
        return songList;
    }

    public void setSongList(ArrayList<Music> songList) {
        this.songList = songList;
    }

}
