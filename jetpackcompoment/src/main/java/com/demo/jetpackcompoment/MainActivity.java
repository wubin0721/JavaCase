package com.demo.jetpackcompoment;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.demo.jetpackcompoment.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private MyTextView myTextView;

    private MyViewModel myViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //使用DataBinding布局，不再使用findviewbyid
        ActivityMainBinding activityMainBinding =
                DataBindingUtil.setContentView(this,R.layout.activity_main);
        //获取ViewModel
        myViewModel = new ViewModelProvider(this, new ViewModelProvider
                .AndroidViewModelFactory(getApplication()))
                .get(MyViewModel.class);
        activityMainBinding.setMyViewModel(myViewModel);
        //感知当前MainActivity生命周期
        activityMainBinding.setLifecycleOwner(this);
//        setContentView(R.layout.activity_main);


        myTextView = findViewById(R.id.mytext);
        //向组件添加lifecylce监听
        getLifecycle().addObserver(myTextView);


    }
}