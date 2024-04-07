package com.demo.costimizedialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity{

    private MyDialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(myDialog == null){
            myDialog = new MyDialog(this);
        }

        myDialog.show();
    }

}