package com.example.shardulnegi12.databaseactivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity{

    EditText e1, e2, e3;
    Button b1, b2;
    String s1,s2,s3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        e1 = findViewById(R.id.editText);
        e2 = findViewById(R.id.editText2);
        e3 = findViewById(R.id.editText3);
        b1 = findViewById(R.id.button);
        b2 = findViewById(R.id.button2);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s1 = e1.getText().toString();
                s2=e2.getText().toString();
                s3=e3.getText().toString();
                register();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,StudentDetails.class);
                startActivity(i);
            }
        });
    }

    public void register() {

        RequestQueue rq = Volley.newRequestQueue(MainActivity.this);
        String url = "https://shardulnegi12.000webhostapp.com/cetpa/register.php";
        StringRequest sr = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(MainActivity.this, "registered Successfully", Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Registered Failed", Toast.LENGTH_LONG).show();
            }
            }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                HashMap<String, String> hm= new HashMap<>();
                hm.put("n", s1);
                hm.put("p",s2);
                hm.put("ph",s3);
                return hm;
            }
        };
        rq.add(sr);
    }
}