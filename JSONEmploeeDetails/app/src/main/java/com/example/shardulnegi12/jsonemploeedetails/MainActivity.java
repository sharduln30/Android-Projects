package com.example.shardulnegi12.jsonemploeedetails;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Employee> emp = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.lvEmployee);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                Intent i = new Intent(MainActivity.this, DetailActivity.class);
                i.putExtra("emp", emp.get(position));
                startActivity(i);
            }
        });
            Download dw= new Download();
            dw.execute("http://192.168.43.128/JsonObjEmployee.txt");
    }

    class Download extends AsyncTask<String,Void,String> {

        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
            dialog = new ProgressDialog(MainActivity.this);
            dialog.setMessage("Please Wait");
            dialog.show();
            super.onPreExecute();

        }

        protected String doInBackground(String... strings) {

            return networkProgramming(strings[0]);
        }

        protected void onPostExecute(String s) {

            try {
                dialog.dismiss();
                if (s != null) {


                    JSONArray array =  new JSONArray(s);

                    for (int i = 0; i < array.length(); i++) {

                        JSONObject jo = array.getJSONObject(i);

                        int id = jo.getInt("id");
                        String name = jo.getString("Name");
                        String designation = jo.getString("Designation");
                        double salary = jo.getDouble("Salary");
                        JSONArray hobbies = jo.getJSONArray("hobbies");
                        String[] h = new String[hobbies.length()];

                        for (int j = 0; j < hobbies.length(); j++) {

                            String hobby = hobbies.getString(j);
                            h[j] = hobby;

                        }

                        Employee employee = new Employee(id, name, designation, salary, h);
                        emp.add(employee);
                    }
                    ArrayAdapter<Employee> adapter = new ArrayAdapter<Employee>(

                            MainActivity.this,
                            android.R.layout.simple_list_item_1,
                            emp);

                    listView.setAdapter(adapter);


                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

    }

    public String networkProgramming(String string){
        try {

            URL url = new URL(string);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            InputStream is = con.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            StringBuilder sb = new StringBuilder();
            String content = null;

            while ((content = br.readLine()) != null) {

                sb.append(content);

            }
            return  sb.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
