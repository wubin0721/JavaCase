package com.demo.jetpackcompoment.navigation;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.demo.jetpackcompoment.R;


public class SecondFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button btn1 = getView().findViewById(R.id.btn2);
        btn1.setOnClickListener((v) -> {
            NavController navController = Navigation.findNavController(v);
            //指定当前fragmen的跳转路线
            navController.navigate(R.id.action_secondFragment2_to_firstFragment);
        });
    }
}