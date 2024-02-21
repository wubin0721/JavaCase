package com.example.broadcast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SendBroadCastActivity extends AppCompatActivity {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_broad_cast);
        editText = this.findViewById(R.id.et_input);
    }

    public void sendBroadcastMsg(View view){
            String connect = editText.getText().toString().trim();
            Intent intent = new Intent();
            intent.setAction(Constants.ACTION_SEND_MSG);
            intent.putExtra(Constants.KEY_CONTENT,connect);
            //发射广播
            sendBroadcast(intent);
    }
}