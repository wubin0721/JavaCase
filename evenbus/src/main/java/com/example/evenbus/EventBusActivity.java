package com.example.evenbus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class EventBusActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "EventBusActivity";
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus);
        button = this.findViewById(R.id.btn1);
        button.setOnClickListener(this);

        EventBus.getDefault().register(this);
    }

    @Subscribe
    public void event(String s){
        Log.d(TAG,s);
    }

//    测试优先级
    @Subscribe(priority = 10,sticky = true)
    public void hot(String s){
        Log.d(TAG,s);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    public void onClick(View v) {
        //        EventBus.getDefault().postSticky("sticky");

        Intent intent = new Intent(this,SecondActivity.class);
        startActivity(intent);
    }
}