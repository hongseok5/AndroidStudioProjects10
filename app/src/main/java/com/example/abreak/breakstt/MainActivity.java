package com.example.abreak.breakstt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.Charset;

public class MainActivity extends AppCompatActivity {

    EditText userId;
    EditText pswd;
    //private final String IP_ADDRESS = "000.000.000.00";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("onCreate()","on CreateStart()");
        userId = (EditText) findViewById(R.id.userId);
        pswd = (EditText) findViewById(R.id.userPswd);

        /*
        Button btnLogin = (Button) findViewById(R.id.btnSubmit);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(),"Button clicked",Toast.LENGTH_LONG).show();

            }
        });
        */
    }

    private String ServerConnected(String strAddress,String strData) {
        String RESULT_TEXT;

        ConnectThread thread = new ConnectThread(strAddress, strData);
        //45.119.147.81
        thread.start();


        RESULT_TEXT = thread.ConnectMessage;


        return RESULT_TEXT;
    }

    public void btnSubmit_Click(View view)
    {
        String loginData =
                userId.getText().toString().trim() + "|" + pswd.getText().toString().trim() + "|";
        String MASSGE = ServerConnected("45.119.147.81",loginData);
        Toast.makeText(getApplicationContext(),MASSGE,Toast.LENGTH_LONG).show();

    }
    /**
     * C에서 받고 전송해주는 데이터는 구조체 Char타입을 기준으로만 설명한다
     * 핸드세이크 모델, 폴링을 계속 던져주면 받는다
     */

    class ConnectThread extends Thread{

        String hostname;
        String loginData;
        String ConnectMessage;

        public ConnectThread(String addr, String data){
            hostname = addr;
            loginData = data;
            ConnectMessage = "로그인 실패하였습니다";
        }

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

}

