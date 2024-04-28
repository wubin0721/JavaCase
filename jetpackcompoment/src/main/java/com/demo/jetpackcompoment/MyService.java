package com.demo.jetpackcompoment;

import android.util.Log;

import androidx.lifecycle.LifecycleService;


//向MyService添加Lifecycle
public class MyService extends LifecycleService{

    public static final String TAG = "MyService";

    public MyService() {
        Log.d(TAG, "MyService: lifecycle ");

        //给MyService添加生命周期
        MyServiceObserver myObserver = new MyServiceObserver(this);
        getLifecycle().addObserver(myObserver);
    }
}
