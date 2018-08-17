package com.cetpainfotech.classifieds;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Post_Ads extends AppCompatActivity {

    EditText etTitle, etDescription;
    ImageView image;
    Button btpost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post__ads);

        etTitle = (EditText)findViewById(R.id.gettitle);
        etDescription = (EditText)findViewById(R.id.etdescription);

        image =(ImageView)findViewById(R.id.image);
        btpost = (Button) findViewById(R.id.btpost);

        btpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ptitle = etTitle.getText().toString();
                String pdesc = etDescription.getText().toString();

                new PostAttempt().execute("http://192.168.43.128/classifieds/post.php?post_title=" + ptitle + "&post_description=" + pdesc);
            }
        });


    }
       class PostAttempt extends AsyncTask<String,Void,String>
    {

        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(Post_Ads.this);
            pd.setMessage("Please Wait..");
            pd.show();
        }

        @Override
        protected String doInBackground(String... strings) {

            try {
                URL url = new URL(strings[0]);
                HttpURLConnection con = (HttpURLConnection)url.openConnection();
                InputStream is = con.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                StringBuilder sb = new StringBuilder();
                String content = null;

                while((content=br.readLine()) != null)
                {
                    sb.append(content);
                }

                return sb.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(String s) {

            if(s!=null)
            {
                try {
                    JSONObject jo = new JSONObject(s);
                    int success = jo.getInt("success");
                    if(success == 1)
                    {

                        String message = jo.getString("message");
                        Toast.makeText(Post_Ads.this,message,Toast.LENGTH_SHORT).show();
                        finish();

                    }
                    else
                    {
                        String message = jo.getString("message");
                        Toast.makeText(Post_Ads.this,message,Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                pd.dismiss();

            }

            super.onPostExecute(s);
        }
    }

}
