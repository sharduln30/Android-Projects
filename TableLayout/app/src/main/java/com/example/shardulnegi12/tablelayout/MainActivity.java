package com.example.shardulnegi12.tablelayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

public class MainActivity extends AppCompatActivity {

    TableLayout t1,t2,t3;
    Button b1,b2,b3,b4,b5,b6,b7,b8,b9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b4 = (Button)findViewById(R.id.Button4);
    }
    public void hide(View view) {

        t1.setColumnCollapsed(0,!t1.isColumnCollapsed(0));
        if(t1.isColumnCollapsed(0)){
            b4.setText("show");
        }
        else b4.setText("hide");
    }
}
