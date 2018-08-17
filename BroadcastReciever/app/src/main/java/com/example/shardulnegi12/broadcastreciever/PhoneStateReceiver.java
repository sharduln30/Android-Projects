package com.example.shardulnegi12.broadcastreciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class PhoneStateReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);

        String phone = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);

        if(state.equals(TelephonyManager.EXTRA_STATE_IDLE)){

            Toast.makeText(context,phone + "disconnected", Toast.LENGTH_SHORT).show();
        }
         if(state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)){

            Toast.makeText(context,phone + "received", Toast.LENGTH_SHORT).show();
        }
        if(state.equals(TelephonyManager.EXTRA_STATE_RINGING)){

            Toast.makeText(context,phone + "ringing", Toast.LENGTH_SHORT).show();
        }
    }
}
