package com.example.shardulnegi12.projectmanager;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    class Upload extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            return uploadFile(strings[0]);
        }
        @Override
        protected void onPostExecute(String s){
            super.onPostExecute(s);

            TextView tv = (TextView) findViewById(R.id.textView);

            if (s != null) {

                tv.setText("Null Value");
            }
        }
    }
    public String uploadFile(String filename) {

        HttpURLConnection conn = null;
        DataOutputStream dos = null;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024;

        //open a HTTP connection to the URL.
        try {
            FileInputStream fileInputStream = new FileInputStream(filename);

            URL url = new URL("http://10.0.2.2/android/upload.php");

            conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("ENCTYPE", "multipart/form-data");
            conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
            conn.setRequestProperty("upload_file", filename);

            dos = new DataOutputStream(conn.getOutputStream());
            dos.writeBytes(twoHyphens+boundary+lineEnd);
            dos.writeBytes("Content-Disposition form-data;name=\"upload_file\";filename=\""+filename+"\""+lineEnd);

            dos.writeBytes(lineEnd);

            bytesAvailable = fileInputStream.available();

            bufferSize = Math.min(bytesAvailable, maxBufferSize);

            buffer = new byte[bufferSize];

            bytesRead = fileInputStream.read(buffer, 0, bufferSize);

            while (bytesRead > 0) {

                dos.write(buffer, 0, bufferSize);
                bytesAvailable = fileInputStream.available();
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                dos.writeBytes(lineEnd);
                dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);


            }

            fileInputStream.close();
            dos.flush();
            dos.close();

            InputStream is = conn.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            StringBuilder sb = new StringBuilder();

            String content = null;

            while ((content = br.readLine())!=null){

                sb.append(content);
            }

            return  sb.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }
}
