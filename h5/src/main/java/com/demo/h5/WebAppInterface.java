package com.demo.h5;

import android.content.Context;
import android.util.Log;
import android.webkit.JavascriptInterface;

public class WebAppInterface {

        private Context mContext;

        private String buttonId;

        private String url;

        WebAppInterface(Context activity) {
            this.mContext = activity;
        }

        //此处与注入H5的函数名称必须一致
        @JavascriptInterface
        public void getButtonId(String buttonId,String url) {
            //获取到 H5里面的Button的 id
            this.buttonId = buttonId;
            //获取当前 H5里面的页面的url地址
            this.url = url;
            print();
        }

        private void print(){
            Log.d("Button ID", "Button ID :"+buttonId);
            Log.d("Button ID", "CurrentUrl :"+url);
        }

}
