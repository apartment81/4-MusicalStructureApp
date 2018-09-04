package com.example.mirodone.musicapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;


import java.util.ArrayList;

public class AlbumsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albums);

        // list of songs
        final ArrayList<Albums> album = new ArrayList<Albums>();

        album.add(new Albums("Dangerous", "Michael Jackson", R.drawable.album_mj));
        album.add(new Albums("No title", "Unknown", R.drawable.album_default));
        album.add(new Albums("The 20/20 Experience", "Justin Timberlake", R.drawable.album_jt));
        album.add(new Albums("No title", "Unknown", R.drawable.album_default));
        album.add(new Albums("No title", "Unknown", R.drawable.album_default));
        album.add(new Albums("No title", "Unknown", R.drawable.album_default));
        album.add(new Albums("No title", "Unknown", R.drawable.album_default));
        album.add(new Albums("No title", "Unknown", R.drawable.album_default));
        album.add(new Albums("No title", "Unknown", R.drawable.album_default));


        AlbumsAdapter adapter = new AlbumsAdapter(this, album);

        GridView gridView = findViewById(R.id.list_albums);

        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent nowPlayingIntent = new Intent(AlbumsActivity.this, SongsActivity.class);
                startActivity(nowPlayingIntent);

            }
        });

    }
}
