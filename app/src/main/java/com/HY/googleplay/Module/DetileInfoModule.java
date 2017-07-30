package com.HY.googleplay.Module;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.text.format.Formatter;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.HY.googleplay.Bean.AppInfo;
import com.HY.googleplay.Http.Url;
import com.HY.googleplay.R;
import com.HY.googleplay.Utils.GlideUtil;

import butterknife.BindView;

/**
 * Created by 杂兵 on 2017/7/28.
 */

public class DetileInfoModule extends BaseModoule<AppInfo> {
    @BindView(R.id.iv_show)
    ImageView ivShow;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.rb)
    RatingBar rb;
    @BindView(R.id.tv_detile1)
    TextView tvDetile1;
    @BindView(R.id.tv_detile2)
    TextView tvDetile2;
    @BindView(R.id.tv_detile3)
    TextView tvDetile3;
    @BindView(R.id.tv_detile4)
    TextView tvDetile4;
    @BindView(R.id.ll_detile_info)
    LinearLayout llDetileInfo;

    public DetileInfoModule(Context context) {
        super(context);
    }

    @Override
    protected int getLayout() {
        return R.layout.detile_info_module;
    }

    @Override
    public void bindData(AppInfo appInfo) {
        GlideUtil.loadCircleImage(Url.ImagePrefix + appInfo.iconUrl, ivShow);
        tvDetile1.setText("版本：" + appInfo.version);
        tvDetile2.setText("下载量：" + appInfo.downloadNum);
        tvDetile3.setText("创建时间：" + appInfo.date);
        tvDetile4.setText("大小:" + Formatter.formatFileSize(context, appInfo.size));
        rb.setRating(appInfo.stars);
        tvName.setText(appInfo.name);

        int height = llDetileInfo.getHeight();
        llDetileInfo.setTranslationY(-height);
        llDetileInfo.setScaleX(2.0f);
        llDetileInfo.setScaleY(2.0f);
        ViewCompat.animate(llDetileInfo)
                .translationY(0)
                .scaleX(1.0f)
                .scaleY(1.0f)
                .setDuration(600)
                .setInterpolator(new BounceInterpolator())
                .start();
    }


}
