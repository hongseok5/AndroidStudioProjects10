package com.example.abreak.daemonservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.example.abreak.util.DataBody;
import com.example.abreak.util.DataHeader;
import com.example.abreak.util.DataMerging;
import com.example.abreak.util.DataType;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class DaemonService extends Service implements Runnable{

    public static final String TAG = "Session Thread Service";
    protected byte[] data;                  // 로그인 데이터를 보내는 스트림
    public String ipAddress;
    protected int port;
    //protected byte[] success = new byte[2]; // 로그인 결과를 받는 스트림
    protected ArrayList<String> params = new ArrayList<String>();
    protected byte flag;
    private int count = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this,
                "[DaemonService] onCreate()함수 호출",
                Toast.LENGTH_SHORT).show();
        Thread myThread = new Thread(this);
        myThread.start();
    }
    @Override
    public int onStartCommand(Intent i, int flags, int sId){

        flag = i.getByteExtra("flag",(byte) 68);
        params = i.getStringArrayListExtra("params");
        ipAddress = i.getStringExtra("ipAddress");
        port = i.getIntExtra("port", 9190);

        Log.d(TAG,"parameter check flag : " + flag);
        Log.d(TAG,"parameter check params : " + params);
        Log.d(TAG,"parameter check ipAddress : " + ipAddress);
        Log.d(TAG,"parameter check port : " + port);




        //Toast.makeText(this, "[DaemonService] onStartCommand() 함수호출", Toast.LENGTH_SHORT).show();
        return START_NOT_STICKY;
        //START_STICKY : 서비스가 강제로 종료되었을 경우 시스템이 다시 서비스를 재시작 시킨다.
        //START_NOT_STICKY :
        //강제로 종료된 서비스가 재시작하지 않으며, 시스템에 의해 강제 종료 되어도 영향을 받지 않는 작업을 진행할 때 사용
        //START_NOT_STICKY : START_STICKY와 비슷하지만 인텐트 값은 그대로 유지
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

    @Override
    public void run(){
        Log.d(TAG, "run method is running");
        Socket socket = null;

        DataHeader dh;
        DataBody db;

        try{
            socket = new Socket();
            dh = new DataHeader( params, flag);
            db = new DataBody( params, flag);
            DataMerging dm = new DataMerging(dh, db);
            socket.connect(new InetSocketAddress(ipAddress, port));
            OutputStream os = socket.getOutputStream();
            InputStream is = socket.getInputStream();
            os.write(dm.getMergedData());
            os.flush();
            byte b  = (byte) is.read();

            if( b == 49){
                Log.d(TAG, "Login Success" + b);
                /*
                while(true){
                    try{
                        os.write((byte) 0x30);
                        os.flush();
                        Log.d(TAG, "Session is retaining");
                        Thread.sleep(10000);
                    } catch(InterruptedException ie){
                        ie.printStackTrace();
                    }
                }
                */
                //Log.d(TAG, "Session is losing");
            } else {

                Log.d(TAG, "Login failed" + b);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try{
                if(socket != null && socket.isClosed() == false){
                    socket.close();
                }
            } catch(IOException e){
                e.printStackTrace();
            }
        }

    }
}
