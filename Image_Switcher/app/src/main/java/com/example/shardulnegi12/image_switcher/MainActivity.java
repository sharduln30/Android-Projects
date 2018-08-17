package com.example.shardulnegi12.image_switcher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
public class MainActivity extends AppCompatActivity {

    public void switchImage(View view) {



        ImageView image = (ImageView) findViewById(R.id.image);
        image.setImageResource(R.drawable.image2);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
