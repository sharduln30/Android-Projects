package com.example.shardulnegi12.newsrss;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Xml;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class NewsActivity extends AppCompatActivity {

    ListView lv2;

    ArrayList<News> newsList = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nws);


        newsList= new ArrayList<>();
        lv2 =findViewById(R.id.lv2);
        class Download extends AsyncTask {

            ProgressDialog dialog;

            protected void onPreExecute() {

                super.onPreExecute();
                dialog = new ProgressDialog(NewsActivity.this);
                dialog.setMessage("Please Wait");
                dialog.show();

            }


            protected void onPostExecute(Void aVoid) {

                super.onPostExecute(aVoid);

                CustomAdapter adapter = new CustomAdapter(NewsActivity.this, (ArrayList<News>) newsList);

                lv2.setAdapter(adapter);


            }


            @Override
            protected Object doInBackground(Object[] objects) {

                return null;

            }

            void parsing(InputStream is) {

                XmlPullParser parser = Xml.newPullParser();

                try {

                    parser.setInput(is, "utf-8");

                    int event = parser.getEventType();

                    News news = null;

                    while (event != XmlPullParser.END_DOCUMENT) {

                        String tag = parser.getName();

                        if (event == XmlPullParser.START_TAG) {
                            if (tag.equals("items") && news == null) {

                                news = new News(null, null, null, null);
                            } else if (tag.equals("title") && news != null) {

                                String title = parser.nextText();

                                news.setTitle(title);

                            } else if (tag.equals("desciption") && news != null) {

                                String description = parser.nextText();
                                try {

                                    int endindex = description.lastIndexOf(".cms") + 4;
                                    int startindex = description.lastIndexOf("https://");

                                    String image = description.substring(startindex, endindex);
                                    news.setImage(image);

                                    int ds = description.indexOf("</a>") + 4;
                                    description = description.substring(ds);


                                } catch (Exception e) {


                                }

                                news.setDiscription(description);
                            }
                            else if(tag.equals("pubdate")&&news!=null){
                                String pubdate = parser.nextText();
                                news.setPubdate(pubdate);
                                newsList.add(news);
                                news = null;

                            }
                        }
                        event = parser.next();
                    }
                    is.close();
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
