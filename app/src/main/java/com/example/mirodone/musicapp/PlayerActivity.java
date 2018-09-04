package com.example.mirodone.musicapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class PlayerActivity extends AppCompatActivity {

    public static int oneTimeOnly = 0;
    private Button btnPause, btnPlay;
    private MediaPlayer mediaPlayer;
    private double startTime = 0;
    private double finalTime = 0;
    private Handler myHandler = new Handler();
    private int forwardTime = 5000;
    private int backwardTime = 5000;
    private SeekBar seekBar;
    private TextView tx1, tx2;

    private Runnable UpdateSongTime = new Runnable() {
        public void run() {
            startTime = mediaPlayer.getCurrentPosition();
            tx1.setText(String.format("%02d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                    TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
                                    toMinutes((long) startTime)))
            );
            seekBar.setProgress((int) startTime);
            myHandler.postDelayed(this, 100);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        Button btnFfw = findViewById(R.id.btn_ff);
        btnPause = findViewById(R.id.btn_pause);
        btnPlay = findViewById(R.id.btn_play);
        Button btnRew = findViewById(R.id.btn_rew);

        tx1 = findViewById(R.id.rev_textView1);
        tx2 = findViewById(R.id.ffw_textView2);

        String displaySongTitle = getIntent().getStringExtra("songTitle");
        TextView currentSongTitle = findViewById(R.id.text_song);
        currentSongTitle.setText(displaySongTitle);

        String displayArtistName = getIntent().getStringExtra("artistName");
        TextView currentArtistName = findViewById(R.id.text_artist);
        currentArtistName.setText(displayArtistName);

        int displayImage = getIntent().getIntExtra("albumImage", 0);
        ImageView currentAlbumImage = findViewById(R.id.imageViewAlbum);
        currentAlbumImage.setImageResource(displayImage);


        int currentSong = getIntent().getIntExtra("currentSong", 0);
        mediaPlayer = MediaPlayer.create(this, currentSong);


        seekBar = findViewById(R.id.seekBar);
        seekBar.setClickable(false);


        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Playing sound", Toast.LENGTH_SHORT).show();
                mediaPlayer.start();

                finalTime = mediaPlayer.getDuration();
                startTime = mediaPlayer.getCurrentPosition();

                if (oneTimeOnly == 0) {
                    seekBar.setMax((int) finalTime);
                }

                tx2.setText(String.format("%02d:%02d",
                        TimeUnit.MILLISECONDS.toMinutes((long) finalTime),
                        TimeUnit.MILLISECONDS.toSeconds((long) finalTime) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
                                        finalTime)))
                );

                tx1.setText(String.format("%02d:%02d",
                        TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                        TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
                                        startTime)))
                );
                seekBar.setProgress((int) startTime);
                myHandler.postDelayed(UpdateSongTime, 100);
                btnPause.setEnabled(true);
                btnPlay.setEnabled(false);
            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Pausing sound ", Toast.LENGTH_SHORT).show();
                mediaPlayer.pause();
                btnPause.setEnabled(false);
                btnPlay.setEnabled(true);
            }
        });

        btnFfw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = (int) startTime;

                if ((temp + forwardTime) <= finalTime) {
                    startTime = startTime + forwardTime;
                    mediaPlayer.seekTo((int) startTime);
                    Toast.makeText(getApplicationContext(), "You have Jumped forward 5 seconds", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Cannot jump forward 5 seconds", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnRew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = (int) startTime;

                if ((temp - backwardTime) > 0) {
                    startTime = startTime - backwardTime;
                    mediaPlayer.seekTo((int) startTime);
                    Toast.makeText(getApplicationContext(), "You have Jumped backward 5 seconds", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Cannot jump backward 5 seconds", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.back_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.mnu_songs:
                Intent songsIntent = new Intent(PlayerActivity.this, SongsActivity.class);
                startActivity(songsIntent);
                resetMP();
                break;
            case R.id.mnu_albums:
                Intent albumsIntent = new Intent(PlayerActivity.this, AlbumsActivity.class);
                startActivity(albumsIntent);
                resetMP();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return super.onOptionsItemSelected(item);
    }

    private void resetMP() {
        if (mediaPlayer != null) {
            mediaPlayer.reset();

            if (myHandler != null)
                myHandler.removeCallbacks(null);
        }

    }

    @Override
    public void onBackPressed() {
        resetMP();
        finish();
        super.onBackPressed();
    }
}
