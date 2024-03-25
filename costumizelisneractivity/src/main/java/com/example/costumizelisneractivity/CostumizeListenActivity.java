package com.example.costumizelisneractivity;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.regex.Pattern;


public class CostumizeListenActivity extends AppCompatActivity
        implements Person.PersonListener{

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_costumize_listen);

        tv = this.findViewById(R.id.tv_hello);

        Person person = new Person();
        //注册Person的监听器
        person.setOnPersonListener(this);
        //这里的use()方法会调用下面必须重写的onMyWay方法
        person.use();


        //正则表达式
        boolean isCorrect =Pattern.matches("a*b","aaaaaab");
        if(isCorrect){
            Log.d("TAG", "Login");
        }

    }

    //这里的onMyWay方法是必须重写的
    @Override
    public void onMyWay(String str) {
        tv.setText(str);
        Log.d("kt", str);
    }
}