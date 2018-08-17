package com.example.shardulnegi12.databaseactivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class StudentDetails extends AppCompatActivity {
    EditText ee1, ee2, ee3, ee4;
    Button bb1;
    String ss1, ss2, ss3, id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_data);

        ee1 = findViewById(R.id.editText4);
        ee2 = findViewById(R.id.editText5);
        ee3 = findViewById(R.id.editText6);
        ee4 = findViewById(R.id.editText7);
        bb1 = findViewById(R.id.button3);

        bb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id = ee1.getText().toString();
                ss1 = ee2.getText().toString();
                ss2 = ee3.getText().toString();
                ss3 = ee4.getText().toString();
                fetch();

            }
        });


    }

    private void fetch() {
        id = ee1.getText().toString();
        if (id.equals(" ")) {
            Toast.makeText(this, "Please enter your name", Toast.LENGTH_LONG).show();
            return;

        }
        String url = Config.DATA_URL + ee1.getText().toString().trim();
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(StudentDetails.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String response) {
        String name = " ";
        String password = " ";
        String con = " ";

        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(response);

            JSONArray result = jsonObject.getJSONArray(Config.JSON_ARRAY);
            JSONObject collegedata = result.getJSONObject(0);
            name = collegedata.getString(Config.KEY_NAME);
            password = collegedata.getString(Config.KEY_pass);
            con = collegedata.getString(Config.KEY_phone);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        ee2.setText(name);
        ee3.setText(password);
        ee4.setText(con);
    }
}