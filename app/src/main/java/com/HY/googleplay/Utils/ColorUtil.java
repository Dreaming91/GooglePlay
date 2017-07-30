package com.HY.googleplay.Utils;

import android.graphics.Color;

import java.util.Random;

/**
 * Created by 杂兵 on 2017/7/26.
 */

public class ColorUtil {
    public static int randomColor() {
        int red = new Random().nextInt(200);
        int green = new Random().nextInt(200);
        int blue = new Random().nextInt(200);
        return Color.rgb(red, green, blue);
    }
}
