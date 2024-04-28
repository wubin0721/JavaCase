package com.demo.jetpackcompoment;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;

public class MyServiceObserver implements LifecycleEventObserver {

    public static final String TAG ="MyServiceObserver";

    private Context context;

    public MyServiceObserver(Context context) {
        this.context = context;
    }

    @Override
    public void onStateChanged(@NonNull LifecycleOwner lifecycleOwner, @NonNull Lifecycle.Event event) {
        //如果是ON_RESUME则打印
        if(event == Lifecycle.Event.ON_RESUME){
                print();
        } else if (event == Lifecycle.Event.ON_DESTROY) {

        } else if (event == Lifecycle.Event.ON_PAUSE) {

        } else if (event == Lifecycle.Event.ON_START) {

        } else if (event == Lifecycle.Event.ON_STOP) {

        }
    }

    private void print(){
        Log.d(TAG, "print: this is onResume status");
    }
}
