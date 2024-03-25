package com.example.launchapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LanunchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LanunchFragment extends Fragment {

    private Context context;

    public static LanunchFragment newInstance(int count,int position, int imageId) {
        LanunchFragment fragment = new LanunchFragment();
        Bundle args = new Bundle();
        args.putInt("count", count);
        args.putInt("position", position);
        args.putInt("imageId", imageId);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = getContext();
        Bundle arguments = getArguments();
        int count = arguments.getInt("count",0);
        int position = arguments.getInt("position",0);
        int imageId = arguments.getInt("imageId",0);
        View view =LayoutInflater.from(context).inflate(R.layout.fragment_lanunch,container,false);
        ImageView iv_launch = view.findViewById(R.id.iv_lanuch);
        RadioGroup rg_indicate = view.findViewById(R.id.rg_incate);
        Button btn_start = view.findViewById(R.id.brn_start);
        iv_launch.setImageResource(imageId);

        //每个页面都分配一组对应的单选按钮
        for(int j = 0;j < count;j++){
            RadioButton radioButton = new RadioButton(context);
            radioButton.setLayoutParams(new ViewGroup.LayoutParams(
               ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT
            ));
            radioButton.setPadding(10,10,10,10);
            rg_indicate.addView(radioButton);
        }

        //当前位置的单选按钮要高亮显示
        ((RadioButton)rg_indicate.getChildAt(position)).setChecked(true);

        //如果是最后一个引导页则显示入口按钮
        if(position == count - 1){
            btn_start.setVisibility(View.VISIBLE);
            btn_start.setOnClickListener(v->{
                    //进入主界面
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
            });
        }

        // Inflate the layout for this fragment
        return view;
    }
}