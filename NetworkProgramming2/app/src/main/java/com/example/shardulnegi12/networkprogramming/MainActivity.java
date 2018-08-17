package com.example.shardulnegi12.networkprogramming;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.textView);

    }

    public void networkCode(View view) {

        Download dw = new Download();
        dw.execute();

    }
    class Download extends AsyncTask {

        ProgressDialog dialog;

        protected  void onPreExecute() {

            dialog = new ProgressDialog(MainActivity.this);
            dialog.setMessage("Please Wait");
            dialog.show();

        }

            @Override
        protected Object doInBackground(Object[] objects) {

             networkProgramming();
             return null;
        }

        protected  void onPostExecute(Object[] o) {

            super.onPostExecute(o);
            dialog.dismiss();
            tv.setText(data);


        }

        public String networkProgramming(){
            try {

                URL url = new URL("http://192.168.43.128/data1.php");

                HttpURLConnection con = (HttpURLConnection) url.openConnection();

                InputStream is = con.getInputStream();

                BufferedReader br = new BufferedReader(new InputStreamReader(is));

                StringBuilder sb = new StringBuilder();
                String content = null;

                while ((content = br.readLine()) != null) {

                    sb.append(content+"\n");

                }
                data =   sb.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}
