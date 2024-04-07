package com.demo.costimizedialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MyDialog{
    private Dialog dialog;
    public TextView ok;
    public TextView cancel;

    private Context context;
    public MyDialog(Context context) {
        this.context = context;
//        View view = LayoutInflater.from(context).inflate(R.layout.dialog_costumize, null);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_linearlayout, null);

        dialog = new Dialog(context);
        dialog.setCancelable(true);
        dialog.setContentView(view);
        ok = view.findViewById(R.id.ok);
        cancel = view.findViewById(R.id.cancel);

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
