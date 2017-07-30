package com.HY.googleplay.Adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.HY.googleplay.Http.Url;
import com.HY.googleplay.Utils.GlideUtil;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.ArrayList;

/**
 * 图片详情界面布局
 * Created by 杂兵 on 2017/7/28.
 */

public class DetileScreenAdapter extends PagerAdapter {

    private ArrayList<String> url;

    public DetileScreenAdapter(ArrayList<String> url) {
        this.url = url;
    }

    @Override
    public int getCount() {
        return url.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        PhotoView photoView = new PhotoView(container.getContext());
        GlideUtil.loadImage(Url.ImagePrefix + url.get(position), photoView);
        photoView.setScaleType(ImageView.ScaleType.FIT_XY);
        container.addView(photoView);
        return photoView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
