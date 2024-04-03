package com.demo.h5;

import android.content.Context;
import android.util.Log;
import android.webkit.JavascriptInterface;

public class WebAppInterface {

        private Context mContext;

        private String buttonId;

        WebAppInterface(Context c) {
            this.mContext = c;
        }

        //此处与注入H5的函数名称必须一致
        @JavascriptInterface
        public void getButtonId(String buttonId) {
            this.buttonId = buttonId;
            //获取到 H5里面的Button的 id
            Log.d("Button ID", buttonId);
        }

}
