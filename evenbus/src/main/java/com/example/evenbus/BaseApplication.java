package com.example.evenbus;

import android.app.Application;
import org.greenrobot.eventbus.EventBus;

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        EventBus.builder().addIndex(new MyEventBusIndex()).installDefaultEventBus();
    }
}
