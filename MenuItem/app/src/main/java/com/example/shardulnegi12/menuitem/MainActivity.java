package com.example.shardulnegi12.menuitem;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_item, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        RelativeLayout rl=(RelativeLayout)findViewById(R.id.rl);
        switch (item.getItemId()){

            case R.id.green: rl.setBackgroundColor(Color.GREEN);
            break;
            case R.id.yellow: rl.setBackgroundColor(Color.YELLOW);
            break;
            case R.id.red: rl.setBackgroundColor(Color.RED);
            break;
            case R.id.blue: rl.setBackgroundColor(Color.BLUE);
            break;
            default: rl.setBackgroundColor(Color.BLACK);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}
