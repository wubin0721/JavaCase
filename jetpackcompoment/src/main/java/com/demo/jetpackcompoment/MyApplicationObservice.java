package com.demo.jetpackcompoment;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;

public class MyApplicationObservice implements LifecycleEventObserver {

    public static final String TAG ="MyApplicationObservice";

    private Context context;

    public MyApplicationObservice(Context context) {
        this.context = context;
    }

    @Override
    public void onStateChanged(@NonNull LifecycleOwner lifecycleOwner, @NonNull Lifecycle.Event event) {
        //如果是ON_RESUME则打印
        if (event == Lifecycle.Event.ON_RESUME) {
            Log.d(TAG, "ON_RESUME");
        }  else if (event == Lifecycle.Event.ON_PAUSE) {
            Log.d(TAG, "ON_PAUSE");
        } else if (event == Lifecycle.Event.ON_START) {
            Log.d(TAG, "ON_START");
        } else if (event == Lifecycle.Event.ON_STOP) {
            Log.d(TAG, "ON_STOP");
        }else if (event == Lifecycle.Event.ON_CREATE) {
            Log.d(TAG, "ON_CREATE");
        }
    }
        private void print(){
            Log.d(TAG, "print: this is onResume status");
        }
}
