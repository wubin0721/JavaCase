package com.demo.h5;

import android.webkit.ValueCallback;

public class webviewJavaScript {

    public static final ValueCallback<String> NULL = null;

    //检测页面里所有按钮的click事件，有点击事件就会执行Android.getButtonId函数
    public static final String JS_BUTTON_ID =
            "const currntUrl = window.location.href;\n" +
            "const buttons = document.querySelectorAll(\"button\");\n" +
            "buttons.forEach(button => {\n" +
            "button.addEventListener(\"click\", () => {\n" +
             "Android.getButtonId(button.id,currntUrl)\n" +
             "});\n" +
             "});";
}
