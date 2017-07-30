package com.HY.googleplay.View;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * Created by 杂兵 on 2017/7/24.
 */

public class RatioView extends AppCompatImageView {
    float ratio = 1f;

    public RatioView(Context context) {
        this(context, null);
    }

    public RatioView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RatioView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        ratio = attrs.getAttributeFloatValue("http://schemas.android.com/apk/res-auto", "ratio", 1f);

    }

    public void setRatio(float ratio) {
        this.ratio = ratio;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = (int) (width / ratio);
        int heightSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightSpec);
    }
}
