package com.HY.googleplay.Adapter;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.view.animation.OvershootInterpolator;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * 所有下拉动画的基类
 * Created by 杂兵 on 2017/7/24.
 */

public class AnimationAdapter<T> extends CommonAdapter<T> {

    public AnimationAdapter(Context context, int layoutId, List<T> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, T t, int position) {
       //将所在条目view缩小
        holder.itemView.setScaleX(0.5f);
        holder.itemView.setScaleY(0.5f);
        //设置放大动画
        ViewCompat.animate(holder.itemView).scaleX(1.0f).scaleY(1.0f)
                .setDuration(1000)
                .setInterpolator(new OvershootInterpolator())
                .start();
    }
}
