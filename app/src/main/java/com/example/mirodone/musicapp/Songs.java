package com.example.mirodone.musicapp;

/**
 * Created by mirodone on 4/5/2018.
 */

public class Songs {

    private String mySongTitle;
    private String myArtistName;
    private int myImageResourceId;
    private int myAudioResourceId;
    private int myAlbumImageId;

    public Songs(String songTitle, String artistName, int imageResourceId, int audioResourceId, int albumImageId) {

        mySongTitle = songTitle;
        myArtistName = artistName;
        myImageResourceId = imageResourceId;
        myAudioResourceId = audioResourceId;
        myAlbumImageId = albumImageId;
    }

    public String getMySongTitle() {
        return mySongTitle;
    }

    public String getMyArtistName() {
        return myArtistName;
    }

    public int getMyImageResourceId() {
        return myImageResourceId;
    }

    public int getMyAudioResourceId() {
        return myAudioResourceId;
    }

    public int getMyAlbumImageId() {
        return myAlbumImageId;
    }
}
