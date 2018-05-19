package com.example.abreak.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by break on 18. 4. 29.
 */

public class BootCompletedReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent){
        if(intent.getAction().equals("android.intent.action.BOOT_COMPLETED")){
            Toast.makeText(context, "BOOT_COMPLETED", Toast.LENGTH_LONG).show();
        }
    }
}
