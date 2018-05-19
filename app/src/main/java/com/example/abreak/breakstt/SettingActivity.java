package com.example.abreak.breakstt;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Toast.makeText(this,"SettingActivity launched", Toast.LENGTH_LONG).show();

        Button callButton = (Button) this.findViewById(R.id.callButton);
        callButton.setOnClickListener(this);

        Button smsButton = (Button) this.findViewById(R.id.smsButton);
        smsButton.setOnClickListener(this);

        Button mainButton = (Button) this.findViewById(R.id.mainButton);
        mainButton.setOnClickListener(this);

        Button webButton = (Button) this.findViewById(R.id.webButton);
        webButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        Intent intent = null;
        Uri uri = null;
        switch(v.getId()) {
            case R.id.callButton:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if(checkSelfPermission(Manifest.permission.CALL_PHONE)
                            != PackageManager.PERMISSION_GRANTED){
                        requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 100);
                        Toast.makeText(this,"agree needed from 6.0", Toast.LENGTH_LONG).show();
                        return;
                    }
                }
                uri = Uri.parse("tel:01032273444");
                intent = new Intent(Intent.ACTION_CALL,uri);
                startActivity(intent);
                break;
            case R.id.smsButton:
                uri = Uri.parse("smsto:01032273444");
                intent = new Intent(Intent.ACTION_SENDTO,uri);
                intent.putExtra("sms_body", "hi");
                startActivity(intent);
                break;
            case R.id.mainButton:
                uri = Uri.parse("mailto:xxxx@gmail.com");
                intent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
                break;
            case R.id.webButton:
                uri = Uri.parse("http://naver.com");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
            default:
                break;

        }
    }
}
