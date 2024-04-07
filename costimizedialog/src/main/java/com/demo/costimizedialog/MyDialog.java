package com.demo.costimizedialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MyDialog{
    private Dialog dialog;
    private TextView ok,cancel;

    private ImageView close;

    private Context context;
    public MyDialog(Context context) {
        this.context = context;
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_costumize, null);
        dialog = new Dialog(context,R.style.customDialog);
        dialog.setCancelable(true);
        dialog.setContentView(view);
        ok = view.findViewById(R.id.ok);
        cancel = view.findViewById(R.id.cancel);
        close = view.findViewById(R.id.close);
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        // 设置背景层透明度
        lp.dimAmount = 0.4f;
        dialog.getWindow().setAttributes(lp);


        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"已确定",Toast.LENGTH_SHORT).show();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        cancel();
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });
    }
    public void show(){
        if(dialog != null){
            dialog.show();
        }
    }
    public void cancel(){
        if(dialog != null){
            dialog.dismiss();
        }
    }


}
