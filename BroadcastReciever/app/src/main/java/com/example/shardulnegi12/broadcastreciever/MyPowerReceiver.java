package com.example.shardulnegi12.broadcastreciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyPowerReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        if(intent.getAction() == Intent.ACTION_POWER_CONNECTED){

            Toast.makeText(context,"power connected",Toast.LENGTH_LONG).show();
        }
        else if(intent.getAction() == Intent.ACTION_POWER_DISCONNECTED){

            Toast.makeText(context,"power disconnected",Toast.LENGTH_LONG).show();
        }
    }

}
