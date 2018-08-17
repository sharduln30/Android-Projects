package com.example.shardulnegi12.networkprogramming;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    TextView tv1, tv2, tv3, tv4;
    String data;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1 = (TextView)findViewById(R.id.textView);
        tv2 = (TextView)findViewById(R.id.textView2);
        tv3 = (TextView)findViewById(R.id.textView3);
        tv4 = (TextView)findViewById(R.id.textView4);
        button = (Button)findViewById(R.id.button4);

        }
        public void getData(View view) {

            Download dw = new Download();
            dw.execute();
        }

        class Download extends AsyncTask {

            ProgressDialog dialog;

            protected void onPreExecute() {

                super.onPreExecute();
                dialog = new ProgressDialog(MainActivity.this);
                dialog.setMessage("Please Wait");
                dialog.show();

            }


            protected void onPostExecute(Object o) {

                super.onPostExecute(o);
                dialog.dismiss();
                String[] words = data.split(" ");
                tv1.setText(words[0] + "\n" + words[1]);
                tv2.setText(words[3] + "\n" + words[4]);
                tv3.setText(words[6] + "\n" + words[7]);
                tv4.setText(words[2] + "\n" + words[5]+"\n"+words[8]);



            }


            @Override
            protected Object doInBackground(Object[] objects) {

                URL url = null;

                try {
                    url = new URL("http://192.168.43.128/StudentData.txt");

                    HttpURLConnection con = null;
                    con = (HttpURLConnection) url.openConnection();

                    InputStream is = null;
                    is = con.getInputStream();

                    BufferedReader br = new BufferedReader(new InputStreamReader(is));

                    StringBuilder sb = new StringBuilder();
                    String content = null;

                    while ((content = br.readLine()) != null) {

                        sb.append(content + " ");

                    }
                    data = sb.toString();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }



                return  null;

            }



        }
}

