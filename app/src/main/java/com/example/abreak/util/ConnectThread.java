package com.example.abreak.util;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by break on 18. 4. 27.
 */

public class ConnectThread extends Thread {

    protected String hostName;
    protected byte[] data;
    protected String ConnectMessage;
    protected int port;

    List<String> Parms = new ArrayList<>();

    public ConnectThread(String addr, int port, List<String> Parms) {
        hostName = addr;
        this.port = port;
        this.Parms = Parms;

    }

    @Override
    public void run() {
        Log.d("run", "runStart!");
        Socket socket = null;


        DataHeader dh;
        DataBody db;

        try {
            socket = new Socket();//1.Socket 생성
            dh = new DataHeader(Parms, (byte) 76);
            db = new DataBody(Parms, (byte) 76);
            DataMerging dm = new DataMerging(dh, db);
            socket.connect(new InetSocketAddress(hostName, port));

            OutputStream os = socket.getOutputStream();

            os.write(dm.getMergedData());

            os.flush();
            InputStream is = socket.getInputStream();
            //ConnectMessage = new String(echoData, 0, readByteCount, "UTF-8");


            os.close();
            is.close();

        } catch (ConnectException e) {
            Log.d("run", "[client] not connect");
            Log.d("run", e.toString());
        } catch (SocketTimeoutException e) {
            Log.d("run", "[client] read timeout");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            Log.d("run", e.toString());
        } finally {
            try {
                if (socket != null && socket.isClosed() == false) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}




/*
    class ConnectThread extends Thread{

        public void run(){
            Log.d("run()","run Start!");
            Socket socket = null;
            try{
                Log.d("Param",this.loginData);
                int port = 9190;
                socket = new Socket();
                socket.connect(new InetSocketAddress(hostname,port));

                byte[] bytes = null;

                OutputStream os = socket.getOutputStream();
                bytes = loginData.getBytes("UTF-8");
                os.write(bytes);
                os.flush();

                InputStream is = socket.getInputStream();
                bytes = new byte[1024];
                int readyByteCount = is.read(bytes);
                loginData = new String(bytes, 0, readyByteCount, "UTF-8");
                // 로그인 성공을 토스트로 띄우기
                Log.d("echoMessage", loginData);

                os.close();
                is.close();
                socket.close();
                ConnectMessage = "로그인 완료 하였습니다";
            } catch ( SocketException se ){
                se.printStackTrace();
            } catch ( IOException ioe ){
                ioe.printStackTrace();
            } catch ( Exception e){
                e.printStackTrace();
            }
            if(!socket.isClosed()){
                try{
                    socket.close();
                }catch ( IOException ie){
                    ie.printStackTrace();
                    socket = null;
                }
            }

        }
    }

 */

