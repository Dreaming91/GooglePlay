package com.HY.googleplay.Adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.HY.googleplay.Utils.GlideUtil;

import java.util.ArrayList;

/**
 *
 * Created by 杂兵 on 2017/7/24.
 */

public class MyHeaderAdapter extends PagerAdapter {
    private ArrayList<String> imgUrlList;

    public MyHeaderAdapter(ArrayList<String> imgUrl) {
        this.imgUrlList = imgUrl;
    }

    @Override
    public int getCount() {
        return imgUrlList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(container.getContext());
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        GlideUtil.loadImage(imgUrlList.get(position), imageView);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);

    }
}
