package com.example.handler;

import android.app.Application;

import me.jessyan.autosize.AutoSizeConfig;

public class MyAplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        AutoSizeConfig.getInstance().setCustomFragment(true);
    }
}
