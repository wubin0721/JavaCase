package com.example.retrofit_rxjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.retrofit_rxjava.Network.ApiService;
import com.example.retrofit_rxjava.Network.exception.ApiException;
import com.example.retrofit_rxjava.Network.exception.ErrorConsumer;
import com.example.retrofit_rxjava.Network.response.ResponseTransformer;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.functions.Consumer;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    @Inject
    ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiService.RxPost("测试数据")
                .compose(ResponseTransformer.obtain())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.i("TAG", "data:" + s);
                    }
                }, new ErrorConsumer() {
                    @Override
                    protected void error(ApiException e) {

                    }
                });
    }
}