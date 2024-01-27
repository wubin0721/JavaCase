package com.example.recycleview;


import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class SMSActivity extends AppCompatActivity implements View.OnClickListener{

    private static final int MSG_START_TIMER = 0;
    private static final int MSG_OK = 1;
    private static Handler handler;
    private EditText editText;

    private int timeCount = 60;

    private Button btnSend,btnConfirm;
    private ImageView code;
    private String verifyCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smsactivity);

        btnSend =   this.findViewById(R.id.btnSend);
        btnConfirm = this.findViewById(R.id.btnConfirm);
        editText =  this.findViewById(R.id.etc);
        code = this.findViewById(R.id.code);


        btnSend.setOnClickListener(this);
        btnConfirm.setOnClickListener(this);


        HandlerThread ht = new HandlerThread("mainHandle");
        ht.start();

        handler = new MyHandler(new WeakReference<>(getWindow().getDecorView()));
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.btnSend){
            handler.sendEmptyMessage(MSG_START_TIMER);
            //网络请求图片验证码
//            AsyncTask<String,Integer, Map<String,Object>> excute = new ImageAsyncTask().
//                    execute("http://10.5.224.156:9001/getVerifiCodeImage");
//            try{
//                    Map<String,Object> map = excute.get();
//                    verifyCode = (String) map.get("code");
//                    Bitmap bitmap =(Bitmap) map.get("bitmap");
//                    if(bitmap != null){
//                        code.setImageBitmap(bitmap);
//                    }
//            }catch(ExecutionException e){
//                    e.printStackTrace();
//            }catch(InterruptedException e){
//                e.printStackTrace();
//            }
        } else if (id == R.id.btnConfirm) {
            String s = editText.getText().toString().trim();
            if(!TextUtils.isEmpty(s)){
                handler.removeMessages(MSG_START_TIMER);
            }
        }
    }


    class MyHandler extends Handler{
        public MyHandler(WeakReference<View> v){

        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case MSG_START_TIMER:
                    btnSend.setText(timeCount--+"s");
                    handler.sendEmptyMessageDelayed(MSG_START_TIMER,1000*1);
                    if(timeCount == 0){
                        handler.removeMessages(MSG_START_TIMER);
                        btnSend.setText("请重新发送短信");
                    }
                    break;
                case MSG_OK:
                    
                    break;
            }
        }
    }




}