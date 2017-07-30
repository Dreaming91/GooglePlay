package com.HY.googleplay.Adapter;

import android.content.Context;
import android.widget.ImageView;

import com.HY.googleplay.Bean.Subject;
import com.HY.googleplay.Http.Url;
import com.HY.googleplay.R;
import com.HY.googleplay.Utils.GlideUtil;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * 专题界面布局
 * Created by 杂兵 on 2017/7/24.
 */

public class SubjectAdapter extends AnimationAdapter<Subject> {
    public SubjectAdapter(Context context, int layoutId, List<Subject> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, Subject subject, int position) {
        super.convert(holder,subject,position);
        holder.setText(R.id.tv_subject_des, subject.des);
        GlideUtil.loadImage(Url.ImagePrefix + subject.url, (ImageView) holder.getView(R.id.iv_subject_show));
    }
}
