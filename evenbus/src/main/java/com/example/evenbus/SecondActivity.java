package com.example.evenbus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "SecondActivity";

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        button = this.findViewById(R.id.btn2);
        button.setOnClickListener(this);

//        EventBus.getDefault().register(this);
    }



//粘性事件接收
    @Subscribe(sticky = true)
    public void sticky(String s){
        Log.d(TAG,s);
    }

    @Override
    public void onClick(View v) {
        //发布事件
        EventBus.getDefault().post("simon");
    }
}