package com.example.mirodone.musicapp;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class SongsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs);

        // list of songs
        final ArrayList<Songs> songs = new ArrayList<Songs>();

        songs.add(new Songs("Dangerous", "Michael Jackson", R.drawable.play_btn_img, R.raw.song_mj_black, R.drawable.album_mj));
        songs.add(new Songs("Black or White", "Michael Jackson", R.drawable.play_btn_img, R.raw.song_no, R.drawable.album_mj));
        songs.add(new Songs("In the Closet", "Michael Jackson", R.drawable.play_btn_img, R.raw.song_no, R.drawable.album_mj));
        songs.add(new Songs("Heal the World", "Michael Jackson", R.drawable.play_btn_img, R.raw.song_no, R.drawable.album_mj));
        songs.add(new Songs("Song title 5", "Artist Name A", R.drawable.play_btn_img, R.raw.song_no, R.drawable.album_default));
        songs.add(new Songs("Suit & Tie", "Justin Timberlake", R.drawable.play_btn_img, R.raw.song_jt_suit, R.drawable.album_jt));
        songs.add(new Songs("TKO", "Justin Timberlake", R.drawable.play_btn_img, R.raw.song_no, R.drawable.album_jt));
        songs.add(new Songs("Mirrors", "Justin Timberlake", R.drawable.play_btn_img, R.raw.song_no, R.drawable.album_jt));
        songs.add(new Songs("Take Back the Night", "Justin Timberlake", R.drawable.play_btn_img, R.raw.song_no, R.drawable.album_jt));
        songs.add(new Songs("Song title 10", "Artist Name B", R.drawable.play_btn_img, R.raw.song_no, R.drawable.album_default));

        // Create an ArrayAdapter, whose data source is a list of Strings. The
        // adapter knows how to create layouts for each item in the list, using the
        // simple_list_item_1.xml layout resource defined in the Android framework.
        // This list item layout contains a single TextView, which the adapter will set to
        // display a single word.

        SongsAdapter adapter = new SongsAdapter(this, songs);

        // Find the ListView object in the view hierarchy of the Activity.
        // There should be a ListView with the view ID called list, which is declared in the
        // activity_songs.xml layout file.
        ListView listView = findViewById(R.id.list_songs);

        // Make the  ListView use the ArrayAdapter we created above, so that the
        //  ListView will display list items for each song in the list of songs.
        // Calling the setAdapter method on the  ListView object and pass in
        // 1 argument, which is the ArrayAdapter with the variable name adapter.
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {


                // Get the song title at the given position the user clicked on
                String currentSongTitle = songs.get(position).getMySongTitle();

                String currentArtistName = songs.get(position).getMyArtistName();

                // Get the image resource at the given position the user clicked on

                int currentSong = songs.get(position).getMyAudioResourceId();

                int currentAlbumImage = songs.get(position).getMyAlbumImageId();


                Intent playIntent = new Intent(SongsActivity.this, PlayerActivity.class);
                playIntent.putExtra("songTitle", currentSongTitle);
                playIntent.putExtra("artistName", currentArtistName);
                playIntent.putExtra("albumImage", currentAlbumImage);
                playIntent.putExtra("currentSong", currentSong);
                startActivity(playIntent);

            }
        });

    }
}
