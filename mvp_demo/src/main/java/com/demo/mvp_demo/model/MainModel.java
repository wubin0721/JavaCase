package com.demo.mvp_demo.model;


import android.os.Handler;
import android.os.Looper;

//M层接收并处理数据
public class MainModel implements Imodel{

    @Override
    public void login(final String username, String password, final Callback callback){

        //利用postDelayed方法模拟网络请求的耗时操作
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(new User("jame","123"));
            }
        },2000);
    }
}
