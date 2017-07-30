package com.HY.googleplay.Utils;

import android.widget.ImageView;

import com.HY.googleplay.R;
import com.bumptech.glide.Glide;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by 杂兵 on 2017/7/22.
 */

public class GlideUtil {

    /**
     * 封装Glide加载图片
     * @param url
     * @param imageView
     */
    public static void loadCircleImage(String url, ImageView imageView){
        Glide.with(imageView.getContext()).load(url)
                .bitmapTransform(new CropCircleTransformation(imageView.getContext()))
                .placeholder(R.drawable.ic_default)
                .error(R.drawable.ic_default)
                .crossFade(300)
                .into(imageView);
    }

    /**
     * 加载图片，但是不是正圆效果
     * @param url
     * @param imageView
     */
    public static void loadImage(String url, ImageView imageView){
        Glide.with(imageView.getContext()).load(url)
                .placeholder(R.drawable.ic_default)
                .error(R.drawable.ic_default)
                .crossFade(300)
                .into(imageView);
    }
}
