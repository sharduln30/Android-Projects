package com.example.shardulnegi12.broadcastreciever;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[]
                {Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.RECEIVE_SMS},1);

        PowerReceiver pr = new PowerReceiver();

        registerReceiver(pr, new IntentFilter(Intent.ACTION_POWER_CONNECTED));

        registerReceiver(pr, new IntentFilter(Intent.ACTION_POWER_DISCONNECTED));

        SmsReceiver sr = new SmsReceiver();
        registerReceiver(sr, new IntentFilter("android.provider.Telephony.SMS_RECEIVED"));



    }
    class PowerReceiver extends BroadcastReceiver {


        @Override
        public void onReceive(Context context, Intent intent) {

            if(intent.getAction() == Intent.ACTION_POWER_CONNECTED){

                Toast.makeText(getBaseContext(),"power connected",Toast.LENGTH_LONG).show();
            }
            else if(intent.getAction() == Intent.ACTION_POWER_DISCONNECTED){

                Toast.makeText(getBaseContext(),"power disconnected",Toast.LENGTH_LONG).show();
            }

        }
    }

    class SmsReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {

            Object[] obj = (Object[]) intent.getExtras().get("pdus");

            SmsMessage message  = SmsMessage.createFromPdu((byte[]) obj[0]);

            String sender = message.getDisplayOriginatingAddress();

            String text = message.getDisplayMessageBody();

            TextView tv = (TextView)findViewById(R.id.tv);

            tv.setText(sender +" : "+text);
        }
    }
}
