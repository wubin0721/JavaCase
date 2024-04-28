package com.demo.jetpackcompoment;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;

//继承LifecycleEventObserver接口
public class MyTextView extends androidx.appcompat.widget.AppCompatTextView implements LifecycleEventObserver {


    public static final String TAG ="MyTextView";

    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private void print(){
        Log.d(TAG, "print: this is onResume status");
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
}
