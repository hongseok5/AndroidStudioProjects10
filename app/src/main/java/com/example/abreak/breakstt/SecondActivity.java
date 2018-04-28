package com.example.abreak.breakstt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

       // Toast.makeText(this, "[B] onCreate() 함수 호출", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onStart(){
        super.onStart();
      //  Log.d("Intent test","Start second activity 2");
     //   Toast.makeText(this, "[B] onStart() 함수 호출", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart(){
        super.onRestart();
       // Toast.makeText(this,"B onRestart() 함수 호출", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume(){
        super.onResume();
      //  Toast.makeText(this, "B onResume() 함수 호출", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause(){
        super.onPause();
     //   Toast.makeText(this, "[B] onPause() 함수 호출", Toast.LENGTH_SHORT).show();
    }
    /*
    @Override void onStop(){
        super.onStop();
        Toast.makeText(this, "[B] onStop 함수 호출", Toast.LENGTH_SHORT).show();
    }

    @Override void onDestroy(){
        super.onDestroy();

        Toast.makeText(this,"B onDestroy() 함수 호출", Toast.LENGTH_SHORT).show();
    }
    */
}
