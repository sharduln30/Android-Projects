package com.example.shardulnegi12.locationtracking;


import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.shardulnegi12.locationtracking.R;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    LocationManager locationManager;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Location location;

        textView = findViewById(R.id.textView);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        } else {

            location=locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if (location != null) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                textView.setText("Location :\n latitude : " + latitude + "\nlongitude : " + longitude);
                geocoding(location);
            }

            locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }


    }
    void geocoding(Location location) {
        Geocoder geocoder=new Geocoder(this, Locale.getDefault());
        if(geocoder!=null)
        {
            List<Address> addresses=null;
            try {
                addresses=geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
            } catch (IOException e) {
                e.printStackTrace();
            }

            Address address=addresses.get(0);

            StringBuilder sb=new StringBuilder();

            for(int i=0;i<address.getMaxAddressLineIndex();i++){
                sb.append(address.getAddressLine(i));
            }
            String state=address.getAdminArea();
            String city=address.getLocality();
            String country=address.getLocality();
            String pin=address.getPostalCode();
            String phone=address.getPhone();

            textView.append("\n address :"+sb.toString()+"\n state : "+state+"\n country : "+country+"\n city : "+city+"\n pin : "+pin+"\n phone : "+phone);


        }
    }
}

