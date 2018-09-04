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
 * Created by mirodone on 4/18/2018.
 */

public class AlbumsAdapter extends ArrayAdapter<Albums> {

    public AlbumsAdapter(Activity context, ArrayList<Albums> albums) {
        super(context, 0, albums);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_album, parent, false);
        }

        Albums currentAlbum = getItem(position);

        TextView albumTitleTextView = listItemView.findViewById(R.id.album_title_text_view);
        albumTitleTextView.setText(currentAlbum.getMyAlbumTitle());

        TextView albumArtistNameTextView = listItemView.findViewById(R.id.album_artist_name_text_view);
        albumArtistNameTextView.setText(currentAlbum.getMyAlbumArtistName());

        ImageView albumImageView = listItemView.findViewById(R.id.album_image);
        albumImageView.setImageResource(currentAlbum.getMyAlbumImageResourceId());


        return listItemView;
    }
}
