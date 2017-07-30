package com.HY.googleplay.Utils;

import android.util.Log;
/**
 * Created by 杂兵 on 2017/7/21.
 */

public class LogUtils {
    private static boolean isDebug = true;

    private static final String TAG = "LogUtils";


    public static void d(String tag, String msg) {
        if (isDebug) {
            Log.d(tag, msg);
        }
    }

    public static void d(String msg) {
        if (isDebug) {
            Log.d(TAG, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (isDebug) {
            Log.e(tag, msg);
        }
    }

    public static void e(String msg) {
        if (isDebug) {
            Log.e(TAG, msg);
        }
    }
}
