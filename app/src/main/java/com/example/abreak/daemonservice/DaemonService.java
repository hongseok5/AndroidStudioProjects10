package com.example.abreak.daemonservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

public class DaemonService extends Service {
    @Override
    public void onCreate(){
        super.onCreate();
        Toast.makeText(this,"[DaemonService] onCreate()함수 호출",Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent i, int flags, int sId){
        Toast.makeText(this, "[DaemonService] onStartCommand() 함수호출", Toast.LENGTH_SHORT).show();
        return START_STICKY;
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Toast.makeText(this, "[DaemonService] onDestroy() 함수 호출", Toast.LENGTH_LONG).show();
    }

    //public DaemonService() {}

    @Override
    public IBinder onBind(Intent intent) {
        return null;
        //throw new UnsupportedOperationException("Not yet implemented");
    }
}
