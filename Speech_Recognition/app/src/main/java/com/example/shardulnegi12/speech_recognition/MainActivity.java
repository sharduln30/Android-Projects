package com.example.shardulnegi12.speech_recognition;

import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
Button btClick;
ListView lv;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        ArrayList<String> text=data.getStringArrayListExtra(
                RecognizerIntent.EXTRA_RESULTS
        );

        ArrayAdapter<String> adapter= new ArrayAdapter<String>(
                this,
                android.R.layout.simple_expandable_list_item_1,text);

                lv.setAdapter(adapter);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       btClick = findViewById(R.id.button);
       lv= findViewById(R.id.list);
        btClick.setOnClickListener(this);


    }
    public void onClick(View view) {

        Intent intent= new Intent(
          RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        startActivityForResult(intent, 1);
    }
}
