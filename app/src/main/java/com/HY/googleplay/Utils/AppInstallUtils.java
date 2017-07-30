package com.HY.googleplay.Utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by 杂兵 on 2017/7/29.
 */

public class AppInstallUtils {

    public static void Install(String filepath, Context context) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.parse("file://" + filepath), "application/vnd.android.package-archive");
        context.startActivity(intent);

    }


}
