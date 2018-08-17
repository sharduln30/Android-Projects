package com.example.shardulnegi12.camerasurfaceview;

import android.Manifest;
import android.bluetooth.le.AdvertisingSetParameters;
import android.graphics.Picture;
import android.hardware.Camera;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback, View.OnClickListener,

        Camera.PictureCallback{

    SurfaceView sv;
    Button bt;
    Camera camera;
    SurfaceHolder surfaceHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

        sv = (SurfaceView) findViewById(R.id.surfaceView);
        bt= (Button) findViewById(R.id.button);

        surfaceHolder = sv.getHolder();
        surfaceHolder.addCallback(this);
        bt.setOnClickListener(this);
    }


    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

        camera = Camera.open();

        Camera.Parameters param;

        camera.setDisplayOrientation(90);

        param= camera.getParameters();

        param.setPreviewSize(352, 288);

        camera.setParameters(param);

        camera.startPreview();

        try {
            camera.setPreviewDisplay(surfaceHolder);
        } catch (IOException e) {
            e.printStackTrace();
        }
        camera.startPreview();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

        camera.stopPreview();
        camera.release();
    }

    @Override
    public void onPictureTaken(byte[] data, Camera camera) {

        try {
            FileOutputStream out = new FileOutputStream((new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            + String.format("/pictureId.jpg",
                    System.currentTimeMillis()))));

            out.write(data);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        camera.startPreview();

    }

    @Override
    public void onClick(View view) {

            camera.takePicture(null, null, this);
    }


}
