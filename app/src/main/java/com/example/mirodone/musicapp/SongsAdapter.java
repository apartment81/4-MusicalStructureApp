package com.example.mirodone.musicapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by mirodone on 4/5/2018.
 */

public class SongsAdapter extends ArrayAdapter<Songs> {

    // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
    // the second argument is used when the ArrayAdapter is populating a single TextView.
    // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
    // going to use this second argument, so it can be any value. Here, we used 0.

    public SongsAdapter(Activity context, ArrayList<Songs> songs) {
        super(context, 0, songs);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Songs currentSong = getItem(position);

        // find the TextView in the list_item.xml layout with the ID song_title
        TextView songTitleTextView = listItemView.findViewById(R.id.song_title_text_view);
        // Get my song title from the current Songs object and
        // set this text on the song title TextView
        songTitleTextView.setText(currentSong.getMySongTitle());

        TextView artistNameTextView = listItemView.findViewById(R.id.artist_name_text_view);
        artistNameTextView.setText(currentSong.getMyArtistName());

        ImageView playView = listItemView.findViewById(R.id.list_play_btn);
        playView.setImageResource(currentSong.getMyImageResourceId());

        ImageView albumView = listItemView.findViewById(R.id.list_album_image);
        albumView.setImageResource(currentSong.getMyAlbumImageId());


        return listItemView;
    }
}
