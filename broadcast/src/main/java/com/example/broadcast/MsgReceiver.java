package com.example.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MsgReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            String content = intent.getStringExtra(Constants.KEY_CONTENT);
    }
}
