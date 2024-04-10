package com.demo.firebasedemo;

import static android.provider.CalendarContract.CalendarCache.URI;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.Iterator;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    public static final String TAG ="MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /*
        app关掉之后，firebase会把通知发去通知栏，点击通知栏会去到启动页，intent有平台发送通知的第
        5项其他选项以key-value形式的信息，可以从以下代码拿取
         */
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if(extras !=  null){
            Set<String> keySet = extras.keySet();
            Iterator<String> iterator = keySet.iterator();
            while (iterator.hasNext()) {
                String key = iterator.next();
                Object value = extras.get(key);
                Log.d(TAG, "key:"+key + ",value:" + value);

                //拿取想要的网址并跳转
                if(key.equals("key")){
                    Uri uri = Uri.parse(String.valueOf(value));
                    Intent vs = new Intent(Intent.ACTION_VIEW,uri);
                    startActivity(vs);
                }
            }
        }


        firebaseInit();

//        Intent intent = new Intent(this, SecondActivity.class);
//        startActivity(intent);
//        overridePendingTransition(R.anim.anim_slide_right_in, R.anim.anim_slide_left_out);
//        finish();
    }

    private void  firebaseInit(){
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
//                        String token = task.getResult();
//                        Log.d(TAG, token);
//                        Toast.makeText(MainActivity.this, token, Toast.LENGTH_SHORT).show();
                    }
                });
    }
}