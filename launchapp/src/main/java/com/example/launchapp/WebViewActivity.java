package com.example.launchapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.webkit.WebView;

public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        //设置全屏显示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        WebView webView = this.findViewById(R.id.webview);
        webView.loadUrl("https://www.mingrisoft.com/Bbs.html");//指定要加载的页面
        webView.getSettings().setUseWideViewPort(true);//设置为可任意比例缩放
        webView.getSettings().setLoadWithOverviewMode(true);//设置加载的内容可自适应屏幕
        //设置具有放大缩小功能
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);

    }
}