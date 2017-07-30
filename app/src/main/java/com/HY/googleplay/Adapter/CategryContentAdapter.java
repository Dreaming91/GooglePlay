package com.HY.googleplay.Adapter;

import android.os.Handler;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;

import com.HY.googleplay.Bean.InfoBean;
import com.HY.googleplay.Http.Url;
import com.HY.googleplay.R;
import com.HY.googleplay.Utils.GlideUtil;
import com.HY.googleplay.Utils.ToastUtils;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

/**
 * 分类页面内容布局
 * Created by 杂兵 on 2017/7/25.
 */

public class CategryContentAdapter implements ItemViewDelegate {
    @Override
    public int getItemViewLayoutId() {
        return R.layout.categry_content;
    }

    @Override
    public boolean isForViewType(Object item, int position) {
        return item instanceof InfoBean;
    }

    @Override
    public void convert(final ViewHolder holder, final Object o, int position) {
        InfoBean infoBean = (InfoBean) o;
        holder.setText(R.id.tv_categry1, ((InfoBean) o).name1);
        GlideUtil.loadCircleImage(Url.ImagePrefix + ((InfoBean) o).url1, (ImageView) holder.getView(R.id.iv_categry1));

        if (!TextUtils.isEmpty(((InfoBean) o).name2) && !TextUtils.isEmpty(((InfoBean) o).url2)) {
            holder.setText(R.id.tv_categry2, ((InfoBean) o).name2);
            GlideUtil.loadCircleImage(Url.ImagePrefix + ((InfoBean) o).url2, (ImageView) holder.getView(R.id.iv_categry2));
        } else {
            holder.setVisible(R.id.ll_categry2, false);
        }
        if (!TextUtils.isEmpty(((InfoBean) o).name3) && !TextUtils.isEmpty(((InfoBean) o).url3)) {
            holder.setText(R.id.tv_categry3, ((InfoBean) o).name3);
            GlideUtil.loadCircleImage(Url.ImagePrefix + ((InfoBean) o).url3, (ImageView) holder.getView(R.id.iv_categry3));
        } else {
            holder.setVisible(R.id.ll_categry3, false);
        }


        holder.setOnClickListener(R.id.ll_categry1, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showToast(holder.itemView.getContext(), ((InfoBean) o).name1);
//                holder.getView(R.id.iv_categry1).setRotationX(0);
//                ViewCompat.animate(holder.getView(R.id.iv_categry1))
//                        .rotationX(360)
//                        .setDuration(800)
//                        .setInterpolator(new OvershootInterpolator())
//                        .start();
                ViewCompat.animate(holder.getView(R.id.iv_categry1))
                        .scaleX(0.5f).scaleY(0.5f)
                        .setDuration(400)
                        .start();
                new Handler() {}.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ViewCompat.animate(holder.getView(R.id.iv_categry1))
                                .scaleX(1.0f).scaleY(1.0f)
                                .setInterpolator(new OvershootInterpolator())
                                .setDuration(400)
                                .start();
                    }
                }, 400);

            }
        });
        holder.setOnClickListener(R.id.ll_categry2, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showToast(holder.itemView.getContext(), ((InfoBean) o).name2);
//                holder.getView(R.id.iv_categry2).setRotationX(0);
//                ViewCompat.animate(holder.getView(R.id.iv_categry2))
//                        .rotationX(360)
//                        .setDuration(800)
//                        .setInterpolator(new OvershootInterpolator())
//                        .start();
                ViewCompat.animate(holder.getView(R.id.iv_categry2))
                        .scaleX(0.5f).scaleY(0.5f)
                        .setDuration(400)
                        .start();
                new Handler() {}.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ViewCompat.animate(holder.getView(R.id.iv_categry2))
                                .scaleX(1.0f).scaleY(1.0f)
                                .setInterpolator(new OvershootInterpolator())
                                .setDuration(400)
                                .start();
                    }
                }, 400);
            }
        });

        holder.setOnClickListener(R.id.ll_categry3, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showToast(holder.itemView.getContext(), ((InfoBean) o).name3);
//                holder.getView(R.id.iv_categry3).setRotationX(0);
//                ViewCompat.animate(holder.getView(R.id.iv_categry3))
//                        .rotationX(360)
//                        .setDuration(800)
//                        .setInterpolator(new OvershootInterpolator())
//                        .start();
                ViewCompat.animate(holder.getView(R.id.iv_categry3))
                        .scaleX(0.5f).scaleY(0.5f)
                        .setDuration(400)
                        .start();
                new Handler() {}.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ViewCompat.animate(holder.getView(R.id.iv_categry3))
                                .scaleX(1.0f).scaleY(1.0f)
                                .setInterpolator(new OvershootInterpolator())
                                .setDuration(400)
                                .start();
                    }
                }, 400);
            }
        });


    }
}
