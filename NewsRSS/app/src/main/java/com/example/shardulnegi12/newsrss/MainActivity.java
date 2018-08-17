package com.example.shardulnegi12.newsrss;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    private String category[] = {"Top Stories", "India", "Sports", "Entertainment", "Science"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView)findViewById(R.id.listView);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,category);
        lv.setAdapter(arrayAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this,NewsActivity.class);
                switch (i)
                {
                    case 0:
                        intent.putExtra("url","https://timesofindia.indiatimes.com/rssfeedstopstories.cms");
                        break;
                    case 1:
                        intent.putExtra("url","https://timesofindia.indiatimes.com/rssfeeds/-2128936835.cms");
                        break;
                    case 2:
                        intent.putExtra("url","https://timesofindia.indiatimes.com/rssfeeds/4719148.cms");
                        break;
                    case 3:
                        intent.putExtra("url","https://timesofindia.indiatimes.com/rssfeeds/1081479906.cms");
                        break;
                    case 4:
                        intent.putExtra("url","https://timesofindia.indiatimes.com/rssfeeds/-2128672765.cms");
                        break;

                }
                startActivity(intent);
            }

        });
    }
}



