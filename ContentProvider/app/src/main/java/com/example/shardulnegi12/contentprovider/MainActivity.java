package com.example.shardulnegi12.contentprovider;

import android.Manifest;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvList;
    ArrayList<String> contactlist = new ArrayList<>();
    ArrayList<String> audiolist = new ArrayList<>();
    ArrayList<String> smslist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[]
                {Manifest.permission.READ_CONTACTS,
                        Manifest.permission.READ_SMS,
                        Manifest.permission.READ_EXTERNAL_STORAGE},1);

        lvList = findViewById(R.id.listView);

    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public  void  contact(View view) {

        ContentResolver cr = getContentResolver();
        Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI,null,null,null,null);
        if(cursor.getCount()>0){

            cursor.moveToFirst();
            do{
                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                String hasPhone = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));

                String phone = "";
                if(Integer.parseInt(hasPhone)>0){

                    Cursor cur  = cr.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " =? ",
                            new String []{id},null);
                    cur.moveToFirst();

                    phone  = cur.getString(cur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                    cur.close();

                }
                contactlist.add(id+" : "+ name + " : "+ phone);
            }while (cursor.moveToNext());

            cursor.close();
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,contactlist);
            lvList.setAdapter(adapter);
        }
        }



    public void audio (View view) {

        ContentResolver cr = getContentResolver();
        Cursor cursor  = cr.query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,null,null,null,null
        );
        if(cursor.getCount()>0){

            cursor.moveToFirst();
            do {
                String name = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.AudioColumns.TITLE));

                String album = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.AudioColumns.ALBUM));

                String path = cursor.getString((cursor.getColumnIndex(MediaStore.Audio.AudioColumns.DATA)));

                audiolist.add(name + " : "+album+" : "+path);
        }while (cursor.moveToNext());
        cursor.close();
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,audiolist);
            lvList.setAdapter(adapter);
    }
    }
    public void sms (View view) {

        ContentResolver cr = getContentResolver();
        Cursor cursor  = cr.query(Uri.parse("content://sms/inbox"),null,null,null,null);

        if(cursor.getCount()>0){

            cursor.moveToFirst();
            do {

                String id = cursor.getString(cursor.getColumnIndex("_id"));

                String address = cursor.getString(cursor.getColumnIndex("address"));

                String body = cursor.getString(cursor.getColumnIndex("body"));

                smslist.add(id + " : "+address+" : "+body);
            }while (cursor.moveToNext());
            cursor.close();
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,smslist);
            lvList.setAdapter(adapter);
        }
}
}


