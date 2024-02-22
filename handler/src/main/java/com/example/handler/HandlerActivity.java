package com.example.handler;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import me.jessyan.autosize.internal.CustomAdapt;

public class HandlerActivity extends AppCompatActivity implements CustomAdapt {

    private TextView mTextview;
    private String message;

    private Handler mHandler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if(msg.what == 0){
                String data = (String)msg.obj;
                mTextview.setText(data);



                Toast.makeText(HandlerActivity.this,
                        "主线程收到消息啦！",Toast.LENGTH_SHORT).show();
            }
        }
    };





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);

        mTextview = this.findViewById(R.id.text);


    }



    public void start(View v){


        //run方法属于子线程，耗时操作在里面执行

        new Thread(new Runnable() {
            @Override
            public void run() {


                message = getMsg();

                //创建 Message对象
                Message msg = new Message();
                msg.what = 0;
                msg.obj = message;

                //子线程往主线程发消息，mHandler的handleMessage处理消息
                //并更新UI
                mHandler.sendMessage(msg);
            }
        }).start();

        Toast.makeText(this,"主线程已经走完！",Toast.LENGTH_SHORT).show();
    }

    public String getMsg(){
        //假装从网络获取了一个字符串
        String reslut = "";
        StringBuilder stringBuilder = new StringBuilder();

        //模拟一个耗时操作
        for(int i=0;i < 100;i++){
            stringBuilder.append("字符串"+i);
        }

        //睡3秒
        try {
            Thread.sleep(3000);
        }catch (Throwable e){
            e.printStackTrace();
        }

        reslut = stringBuilder.toString();

        return  reslut;
    }

    @Override
    public boolean isBaseOnWidth() {
        return false;
    }

    @Override
    public float getSizeInDp() {
        return 667;
    }
}