package com.example.shardulnegi12.videoplayer;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    VideoView videoplayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoplayer = findViewById(R.id.videoView);

        videoplayer.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.vid));

        videoplayer.setMediaController(new MediaController(this));
        videoplayer.start();

    }
}
