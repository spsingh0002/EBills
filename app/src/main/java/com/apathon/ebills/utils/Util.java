package com.apathon.ebills.utils;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by Suraj on 09-05-2015.
 */
public class Util {
    public static final boolean isEmptyOrNull(String s) {
        return s == null || s.trim().equals("");
    }
    public static final int convertToPx(Context mContext, int inDp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, inDp, mContext.getResources().getDisplayMetrics());
    }
}
