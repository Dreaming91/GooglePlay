package com.HY.googleplay.Utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by 杂兵 on 2017/7/21.
 */

public class ToastUtils {

    static Toast toast;

    public static void showToast(Context context, String msg) {
        if (toast == null) {
            toast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
        } else {
            toast.setText(msg);
        }

        toast.show();

    }
}
