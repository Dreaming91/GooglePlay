package com.HY.googleplay.Utils;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;

/**
 * Created by 杂兵 on 2017/7/26.
 */

public class DrableUtils {


    public static GradientDrawable createGD(float radiu) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.RECTANGLE);
        gradientDrawable.setCornerRadius(radiu);
        gradientDrawable.setColor(ColorUtil.randomColor());
        return gradientDrawable;
    }

    public static StateListDrawable createSD(Drawable pressed, Drawable normal) {

        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, pressed);
        stateListDrawable.addState(new int[]{}, normal);
        stateListDrawable.setEnterFadeDuration(800);
        stateListDrawable.setExitFadeDuration(800);
        return stateListDrawable;
    }

}
