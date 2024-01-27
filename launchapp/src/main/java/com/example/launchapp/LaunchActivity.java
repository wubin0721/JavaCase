package com.example.launchapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

public class LaunchActivity extends AppCompatActivity {

    private ViewPager viewPager;

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
        LaunchAdapter launchAdapter = new LaunchAdapter(getSupportFragmentManager(),lanuchArray);
        viewPager.setAdapter(launchAdapter);
    }
}