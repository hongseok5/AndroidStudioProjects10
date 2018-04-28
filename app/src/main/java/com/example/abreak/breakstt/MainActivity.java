package com.example.abreak.breakstt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.abreak.daemonservice.DaemonService;
import com.example.abreak.util.ConnectThread;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText userId;
    EditText pswd;
    private static final String SERVER_IP = "45.119.147.81";
    private static final int SERVER_PORT = 9190;
    //private final String IP_ADDRESS = "000.000.000.00";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("onCreate()","on CreateStart()");
        userId = (EditText) findViewById(R.id.userId);
        pswd = (EditText) findViewById(R.id.userPswd);

        Button btnLogin = (Button) findViewById(R.id.btnSubmit);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(),"Button clicked",Toast.LENGTH_LONG).show();
                List<String> params = new ArrayList<>();

                params.add(userId.getText().toString());
                params.add(pswd.getText().toString());
                ConnectThread thread = new ConnectThread(SERVER_IP,SERVER_PORT,params);
                thread.start();
                /*
                String message = connectResult(SERVER_IP,SERVER_PORT,params);
                Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
                */
            }
        });
        //DaemonService 연습
        Button btnJoin = (Button) findViewById(R.id.btnJoin);
        btnJoin.setOnClickListener(this);
        Button btnTomain = (Button) findViewById(R.id.btnToMain);
        btnTomain.setOnClickListener(this);


        //Intent 연습
        Button btnModify = (Button) findViewById(R.id.btnModify);
        btnModify.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });
        Button btnFindPw = (Button) findViewById(R.id.btnFindPw);
    }

    @Override
    public void onClick(View v){
        Intent intent = null;
        switch(v.getId())
        {
            case R.id.btnJoin:
                intent = new Intent(this, DaemonService.class);
                startService(intent);
                break;
            case R.id.btnToMain:
                intent = new Intent(this, DaemonService.class);
                this.stopService(intent);
                break;
            default:
                break;
        }
    }

    private String connectResult(String ipAddress,int port, List<String> params) {
        String RESULT_TEXT;
        ConnectThread thread = new ConnectThread(ipAddress, port, params);
        //45.119.147.81
        thread.start();
        RESULT_TEXT = thread.message;
        return RESULT_TEXT;
    }
    /**
     * C에서 받고 전송해주는 데이터는 구조체 Char타입을 기준으로만 설명한다
     * 핸드세이크 모델, 폴링을 계속 던져주면 받는다
     */

}

