package com.example.service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;

public class ServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
    }

    public void startService(View view){
        startService(new Intent(this, MyService.class));
    }

    public void stopService(View view){
        stopService(new Intent(this, MyService.class));
    }


    //Activity与 Service的连接 mConnection
    private  ServiceConnection mConnection;
    public void bindService(View view){
        if(mConnection == null){
            mConnection = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {

                }
                @Override
                public void onServiceDisconnected(ComponentName name) {

                }
            };
        }
        bindService(new Intent(this, MyService.class),
                mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mConnection!=null){
            unbindService(mConnection);
            mConnection = null;
        }

    }

}