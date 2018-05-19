package com.example.abreak.breakstt;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.abreak.daemonservice.DaemonService;
import com.example.abreak.receiver.SessionReceiver;
import com.example.abreak.util.ConnectThread;
import com.example.abreak.util.DataType;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText userId;
    EditText pswd;
    private static final String SERVER_IP = "45.119.147.81";
    private static final int SERVER_PORT = 9190;


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

                /*
                *
                * */


                //Toast.makeText(getApplicationContext(),"Button clicked",Toast.LENGTH_LONG).show();
                ArrayList<String> params = new ArrayList<>();

                params.add(userId.getText().toString());
                params.add(pswd.getText().toString());
                //ConnectThread thread = new ConnectThread(SERVER_IP,SERVER_PORT,params);
                //thread.start();

                String message = connectResult(SERVER_IP,SERVER_PORT, params, DataType.getDeleteCode());
                Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();

            }
        });
        //DaemonService 연습
        Button btnJoin = (Button) findViewById(R.id.btnJoin);
        btnJoin.setOnClickListener(this);
        Button btnTomain = (Button) findViewById(R.id.btnToMain);
        btnTomain.setOnClickListener(this);

        //Intent 연습
        Button btnStt = (Button) findViewById(R.id.btnStt);
        btnStt.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, SttActivity.class);
                startActivity(intent);
            }
        });

        Button btnFindPw = (Button) findViewById(R.id.btnFindPw);

        /*박찬순 테스트코드 Start*/

        AlarmManager alarmManager = (AlarmManager)this.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(this, SessionReceiver.class);
        PendingIntent pIntent =
                PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);


        Calendar calendar = Calendar.getInstance();
        //알람시간 calendar에 set해주기

        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 13, 43, 30);

        //알람 예약
        //alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pIntent);
        //alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),2000, pIntent);


        // 반복성 알람 등록d
        long current = System.currentTimeMillis();

        long interval = 1000 * 60;        // 60초 주기반복
        Log.d("알람","알람 30초 주기로 반복!!");

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,  current, interval, pIntent);

        alarmManager.cancel(pIntent);


        /*박찬순 테스트코드 End*/
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

    private String connectResult(String ipAddress,int port, ArrayList<String> params, byte flag) {
        String RESULT_TEXT = "LoginSuccess";
        //ConnectThread thread = new ConnectThread(ipAddress, port, params);
        //45.119.147.81

        Intent intent = new Intent(this, DaemonService.class);
        intent.putExtra("ipAddress", ipAddress);
        intent.putExtra("port", port);
        intent.putStringArrayListExtra("params", params);
        intent.putExtra("flag",flag);
        startService(intent);

        return RESULT_TEXT;
    }
    /**
     * C에서 받고 전송해주는 데이터는 구조체 Char타입을 기준으로만 설명한다
     * 핸드세이크 모델, 폴링을 계속 던져주면 받는다
     */

}

