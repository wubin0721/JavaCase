package com.example.retrofit_rxjava.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.TypedValue;

public class ContverUtils {

    public int dpToPx(Context context, float dp) {
        if (context == null) {
            return -1;
        }

        final float scale = context.getResources().getDisplayMetrics().density;
            return (int) (dp * scale + 0.5f);
    }


}
