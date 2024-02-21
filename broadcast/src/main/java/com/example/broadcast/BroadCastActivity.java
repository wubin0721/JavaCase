package com.example.broadcast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

public class BroadCastActivity extends AppCompatActivity {

    private static final String TAG = "BroadCastActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broad_cast);

        //收听频道：电量变化
        IntentFilter intentFilter = new IntentFilter();
        //设置频道
        intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        BatteryLevelReceiver receiver = new BatteryLevelReceiver();
        //动态注册广播
        this.registerReceiver(receiver,intentFilter);

    }

    /*
    * 创建一个广播接受者，继承自 BroadcastReceiver
    */
    private class BatteryLevelReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.d(TAG,"action is :"+action);
        }
    }
}