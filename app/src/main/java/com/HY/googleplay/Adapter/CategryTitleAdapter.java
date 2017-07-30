package com.HY.googleplay.Adapter;

import com.HY.googleplay.R;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

/**
 * 分类页面标题布局
 * Created by 杂兵 on 2017/7/25.
 */

public class CategryTitleAdapter implements ItemViewDelegate {
    @Override
    public int getItemViewLayoutId() {
        return R.layout.categry_title;
    }

    @Override
    public boolean isForViewType(Object item, int position) {
        return item instanceof String;
    }

    @Override
    public void convert(ViewHolder holder, Object o, int position) {
        holder.setText(R.id.tv_categry_title, (String) o);
    }
}
