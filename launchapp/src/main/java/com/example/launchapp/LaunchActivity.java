package com.example.launchapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class LaunchActivity extends AppCompatActivity {

    private ViewPager2 viewPager;

    private boolean isFirst= false;

    //声明引导页面的图片数组
    private int[] lanuchArray = {
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_foreground,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_foreground
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        viewPager = findViewById(R.id.view_pager);
        LaunchAdapter launchAdapter = new LaunchAdapter(getSupportFragmentManager(), getLifecycle(),lanuchArray);
        viewPager.setAdapter(launchAdapter);

        SharedPreferences sp = getSharedPreferences("FirstLaunch",MODE_PRIVATE);
        isFirst = sp.getBoolean("FirstValue",true);//第一次获取不到值，取默认值true
        if(isFirst){

        }else{
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
        }

    }
}