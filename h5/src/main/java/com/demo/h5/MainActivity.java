package com.demo.h5;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.cn_webview);

        WebSettings webSettings = webView.getSettings();

        webSettings.setJavaScriptEnabled(true);                         //支持js
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(true);

        webView.loadUrl("file:///android_asset/android.html");
        webView.addJavascriptInterface(new WebAppInterface(this), "Android");
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                injectJavasSript(webView);
            }
        });
    }

    private void injectJavasSript(WebView view) {
        //此处必须知道Button在H5的id值才能获取
        //Android.xxx必须与@JavascriptInterface的函数名一致
        //jsCode是注入到H5的js代码
        String jsCode=
                "var button = document.getElementById('myButton');\n"+
                "button.onclick = function(){\n"+
                "//Android.xxx是调用@JavascriptInterface里的安卓方法\n"+
                "Android.getButtonId(this.id)"+
                "};";

        webView.evaluateJavascript(jsCode, value ->{
            //这里处理js执行后的结果
        });
    }




}