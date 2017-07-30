package com.HY.googleplay.Globle;

import android.app.Application;
import android.os.Environment;

import java.io.File;

/**
 * Created by 杂兵 on 2017/7/29.
 */

public class HelloGoogle extends Application {
    public static String downloadPakage = null;

    @Override
    public void onCreate() {
        super.onCreate();
        downloadPakage = Environment.getExternalStorageDirectory() + "/" + getPackageName() + "/download";
        File file = new File(downloadPakage);
        if (!file.exists()) {
            file.mkdirs();
        }

    }
}
