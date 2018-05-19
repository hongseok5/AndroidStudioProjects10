package com.example.abreak.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by break on 18. 5. 5.
 */

public class SessionReceiver extends BroadcastReceiver {

    /*
    * 알람이 울릴때 처리해야하는 메서드
    * */
    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d("Receiver","알람이에요");
    }
}
