package com.example.shardulnegi12.swap_image_switcher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button b1;
    ImageSwitcher is;

    int images[] = { R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4, R.drawable.image5};
    int current = 0;

    float x1, x2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = findViewById(R.id.button);
        b1.setOnClickListener(this);
        is = findViewById(R.id.imagesw);

    }

    @Override
    public void onClick(View view) {

        if (current < images.length-1){

            ++current;
        }
        else {

            current=0;
        }
        ((ImageView) is.getNextView()).setImageResource(images[current]);
        is.showNext();

    }
    public boolean onTouchEvent (MotionEvent event) {


        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
            x1=event.getX();
            break;

            case MotionEvent.ACTION_UP:
            x2=event.getX();
            if(x1<x2) {

                if(current > 0) {
                    --current;
                    ((ImageView) is.getNextView()).setImageResource(images[current]);
                    is.showNext();

                }
                else{

                    Toast.makeText(MainActivity.this, "No previous image", Toast.LENGTH_SHORT).show();
                }
            }
            else
                if(current<images.length-1){

                    ++current;
                    ((ImageView)is.getNextView()).setImageResource(images[current]);
                    is.showNext();

                }
                else{

                    Toast.makeText(MainActivity.this, "No Next Image", Toast.LENGTH_SHORT).show();

                }
            case MotionEvent.ACTION_MOVE:
                break;
        }
        return super.onTouchEvent(event);
    }
}
