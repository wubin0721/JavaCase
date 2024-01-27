package com.example.retrofit_rxjava.utils;

import android.content.Context;

public class ContverUtils {

    public int dpToPx(Context context, float dp) {
        if (context == null) {
            return -1;
        }
        return (int) (dp * context.getResources().getDisplayMetrics().density);
    }

}
