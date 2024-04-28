package com.demo.jetpackcompoment;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class MyViewModel extends AndroidViewModel {

    //定义liveData
    private MutableLiveData<Integer> number;

    public MutableLiveData<Integer> getNumber() {
        if(number == null){
            number = new MutableLiveData<>();
            number.setValue(0);
        }
        return number;
    }


    public void plusnumber(int i){
        number.setValue(number.getValue()+1);
    }

    //ViewMode需要传Context时，使用参数application
    public MyViewModel(@NonNull Application application) {
        super(application);
    }


}
