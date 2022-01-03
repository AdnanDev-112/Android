package com.example.mymusic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

public class PlaySong extends AppCompatActivity {
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
        mediaPlayer.release();
        updateSeekBar.interrupt();
    }

    TextView textview;
    ImageView play,previous,next;
    ArrayList<File> songs;
    MediaPlayer mediaPlayer;
    String songName ;
    int position ;
    SeekBar seekBar;
    Thread updateSeekBar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_song);
        textview = findViewById(R.id.textView);
        play = findViewById(R.id.play);
        previous = findViewById(R.id.previous);
        next = findViewById(R.id.next);
        seekBar = findViewById(R.id.seekBar);


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        songs = (ArrayList) bundle.getParcelableArrayList("songList");
        songName = intent.getStringExtra("currentSong");
        textview.setText(songName);
        textview.setSelected(true);
        position = intent.getIntExtra("position", 0);
        Uri uri = Uri.parse(songs.get(position).toString());
        mediaPlayer = MediaPlayer.create(this, uri);
        mediaPlayer.start();
        seekBar.setMax(mediaPlayer.getDuration());

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            mediaPlayer.seekTo(seekBar.getProgress());
            }
        });
        updateSeekBar = new Thread(){
            @Override
            public void run() {
                super.run();
                int currentPosition = 0;
                try {
                    while(currentPosition < mediaPlayer.getDuration()){
                        currentPosition = mediaPlayer.getCurrentPosition();
                        seekBar.setProgress(currentPosition);
                        sleep(800);
                    }
                }catch(Exception e){

                }
            }
        };
        updateSeekBar.start();


        play.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(mediaPlayer.isPlaying()){
                    play.setImageResource(R.drawable.play);
                    mediaPlayer.pause();
                }else{
                    play.setImageResource(R.drawable.pause);
                    mediaPlayer.start();
                }
            }
        });

            previous.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                mediaPlayer.stop();
                mediaPlayer.release();
              if(position !=0){
                  position =position -1 ;

              }else{
                  position = songs.size() -1 ;

              }
                Uri uri = Uri.parse(songs.get(position).toString());
                mediaPlayer = MediaPlayer.create(getApplicationContext(), uri);
                mediaPlayer.start();
                seekBar.setMax(mediaPlayer.getDuration());
                songName = songs.get(position).getName().toString();
                textview.setText(songName);
                play.setImageResource(R.drawable.pause);
            }

        });
            next.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                mediaPlayer.stop();
                mediaPlayer.release();
              if(position != songs.size() -1){
                  position =position + 1 ;

              }else{
                  position = 0 ;

              }
                Uri uri = Uri.parse(songs.get(position).toString());
                mediaPlayer = MediaPlayer.create(getApplicationContext(), uri);
                mediaPlayer.start();
                seekBar.setMax(mediaPlayer.getDuration());
                songName = songs.get(position).getName().toString();
                textview.setText(songName);
                play.setImageResource(R.drawable.pause);
            }

        });





    }
}