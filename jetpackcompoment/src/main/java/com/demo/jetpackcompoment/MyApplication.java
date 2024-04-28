package com.demo.jetpackcompoment;

import android.app.Application;

import androidx.lifecycle.ProcessLifecycleOwner;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ProcessLifecycleOwner.get().getLifecycle().addObserver(new MyApplicationObservice(this));
    }
}
