package com.example.shardulnegi12.jsonemploeedetails;

import android.os.Bundle;
import android.widget.TextView;
import android.support.v7.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    TextView tv1, tv2, tv3, tv4, tv5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail);
        tv1 = findViewById(R.id.textView);
        tv2 = findViewById(R.id.textView2);
        tv3 = findViewById(R.id.textView3);
        tv4 = findViewById(R.id.textView4);
        tv5 = findViewById(R.id.textView5);
        Employee em = (Employee) getIntent().getExtras().get("emp");

        int id = em.getId();

        String name = em.getName();

        double salary = em.getSalary();

        String designation = em.getDesignation();

        String[] hobbies = em.getHobbies();

        tv1.setText(String.valueOf(id));
        tv2.setText(name);
        tv3.setText(String.valueOf(salary));
        tv4.setText(designation);
        tv5.setText("");

        for (int i = 0; i < hobbies.length; i++) {
            tv5.append((hobbies[i] + "\n"));
        }

    }
}
