package com.example.shardulnegi12.viewpager;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tab = (TabLayout) findViewById(R.id.tab);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tab.setupWithViewPager(viewPager);

    }

    public class ChatFragment

    public CharSequence getPageTitle(int position) {

        switch (position) {

            case 0:
                return "Chat";
            case 1:
                return "Contact";
            case 2:
                return "Status";

        }
    }
}