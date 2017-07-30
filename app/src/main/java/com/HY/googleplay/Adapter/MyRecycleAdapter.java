package com.HY.googleplay.Adapter;

import android.content.Context;
import android.text.format.Formatter;
import android.widget.ImageView;

import com.HY.googleplay.Bean.AppInfo;
import com.HY.googleplay.Http.Url;
import com.HY.googleplay.R;
import com.HY.googleplay.Utils.GlideUtil;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Home页面布局
 * Created by 杂兵 on 2017/7/22.
 */

public class MyRecycleAdapter extends AnimationAdapter<AppInfo> {


    public MyRecycleAdapter(Context context, int layoutId, List<AppInfo> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, AppInfo appInfo, int position) {
        super.convert(holder,appInfo,position);
        holder.setText(R.id.tv_des, appInfo.des)
                .setText(R.id.tv_name, appInfo.name)
                .setRating(R.id.rb, appInfo.stars)
                .setText(R.id.tv_size, Formatter.formatFileSize(holder.itemView.getContext(), appInfo.size));
        GlideUtil.loadCircleImage(Url.ImagePrefix + appInfo.iconUrl, (ImageView) holder.getView(R.id.iv_show));


    }
}
