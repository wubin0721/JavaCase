package com.demo.shortcut;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        Intent intent = getIntent();
        String first = intent.getStringExtra("abc");
        String second = intent.getStringExtra("cde");
        if(second != null){
            if(second.equals("second")){
                intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        }
    }
}