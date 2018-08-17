package com.example.shardulnegi12.videorecoroder;

import android.Manifest;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaCas;
import android.media.MediaRecorder;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback, View.OnClickListener, Camera.PictureCallback{

    SurfaceView sv;
    SurfaceHolder sh;
    Chronometer ch;
    MediaRecorder mr;
    Button bt;

    Camera camera;

    boolean isRecording=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO}, 1);

        sv = (SurfaceView)findViewById(R.id.surfaceView);
        ch = (Chronometer) findViewById(R.id.chrono);
        bt = (Button) findViewById(R.id.button);

        sh = sv.getHolder();
        sh.addCallback(this);
        bt.setOnClickListener(this);

        mr = new MediaRecorder();
        mr.setCamera(camera);
        mr.setAudioSource(MediaRecorder.AudioSource.MIC);
        mr.setVideoSource(MediaRecorder.VideoSource.CAMERA);

        CamcorderProfile high = CamcorderProfile.get(CamcorderProfile.QUALITY_HIGH);
        mr.setProfile(high);

        mr.setOutputFile(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                + String.format("/Video.mp4",
                System.currentTimeMillis()));
        mr.setPreviewDisplay(sh.getSurface());

        try {
            mr.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    @Override
    public void onPictureTaken(byte[] bytes, android.hardware.Camera camera) {

    }
    void stopRecording() {

        mr.stop();
        mr.release();
    }
    void startRecording() {

        mr.start();

    }
    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

        camera = android.hardware.Camera.open();
        //camera.Parameters

        try {
            camera.setPreviewDisplay(sh);
        } catch (IOException e) {
            e.printStackTrace();
        }
        camera.startPreview();

    }
    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void onClick(View view) {

        if(isRecording) {

            ch.stop();
            stopRecording();
        }
        else {
            isRecording = true;
            ch.start();
            startRecording();
        }

    }
}

