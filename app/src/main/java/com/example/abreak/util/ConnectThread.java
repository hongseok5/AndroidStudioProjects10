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
    public String message;
    protected int port;
    protected byte[] success = new byte[2];
    protected List<String> params = new ArrayList<>();

    public ConnectThread(String addr, int port, List<String> params) {
        hostName = addr;
        this.port = port;
        this.params = params;
        message = "Login failed";

    }

    @Override
    public void run() {
        Log.d("run", "runStart!");
        Socket socket = null;

        DataHeader dh;
        DataBody db;

        try {
            socket = new Socket();//1.Socket 생성
            dh = new DataHeader(params, DataType.getLoginCode());
            db = new DataBody(params, DataType.getLoginCode());
            DataMerging dm = new DataMerging(dh, db);
            socket.connect(new InetSocketAddress(hostName, port));

            OutputStream os = socket.getOutputStream();
            InputStream is = socket.getInputStream();
            //Login 성공 여부를 받기 위한 스트림





            os.write(dm.getMergedData());
            os.flush();
            if(is.read() == 80){
                //로그인 성공 여부에 따라 서버에서 보내주는 데이터로 분기
                Log.d("ConnectThread", "로그인 성공 ");
                message = "Login success";
                //startSession();
            } else {
                Log.d("ConnectThread", "로그인 실패 ");
            }

            os.close();
            is.close();
            socket.close();

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


