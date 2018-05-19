package com.example.abreak.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by break on 18. 4. 29.
 */

public class PackageStateReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent){
        if(intent.getAction().equals("android.intent.action.PACKAGE_ADDED")){
            Toast.makeText(context, "PACKAGE_ADDED",Toast.LENGTH_LONG).show();
        } else if(intent.getAction().equals("android.intent.action.PACKAGE_DATA_CLEARED")){
            Toast.makeText(context,"PACKAGE_DATA_CLEARED",Toast.LENGTH_LONG).show();
        } else if(intent.getAction().equals("android.intent.action.PACKAGE_REMOVED")){
            Toast.makeText(context,"PACKAGE_REMOVED", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, "Unknown State", Toast.LENGTH_LONG).show();
        }
    }
}
