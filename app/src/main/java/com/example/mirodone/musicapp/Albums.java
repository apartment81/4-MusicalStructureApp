package com.example.mirodone.musicapp;

/**
 * Created by mirodone on 4/12/2018.
 */

public class Albums {

    private String myAlbumTitle;
    private String myAlbumArtistName;
    private int myAlbumImageResourceId;

    public Albums(String AlbumTitle, String AlbumArtistName, int AlbumImageResourceId) {
        myAlbumTitle = AlbumTitle;
        myAlbumArtistName = AlbumArtistName;
        myAlbumImageResourceId = AlbumImageResourceId;
    }

    public String getMyAlbumTitle() {
        return myAlbumTitle;
    }

    public String getMyAlbumArtistName() {
        return myAlbumArtistName;
    }

    public int getMyAlbumImageResourceId() {
        return myAlbumImageResourceId;
    }
}

