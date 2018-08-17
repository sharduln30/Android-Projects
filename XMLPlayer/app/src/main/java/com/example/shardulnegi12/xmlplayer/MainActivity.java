package com.example.shardulnegi12.xmlplayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Xml;
import android.view.View;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView)findViewById(R.id.textView);
    }
    public void parsing(View view) {

        try {
        InputStream is = getResources().openRawResource(R.raw.student);

        XmlPullParser parser = Xml.newPullParser();


            parser.setInput(is, "utf-8");
            int event = parser.getEventType();


        while(event != XmlPullParser.END_DOCUMENT);
        {
            String tag = parser.getName();
            if(event == Xml.parse()) {


        else if(tag.equals("name")) {

                String name  = parser.nextText();
                tv.append(" : " + name);
        }
        else if(tag.equals("age")) {

            String age  = parser.nextText();
            tv.append(" : " + age + "\n");
        }

    }

        }
is.close();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
}
}