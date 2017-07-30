package com.HY.googleplay.Module;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.HY.googleplay.Activity.DetileImageActivity;
import com.HY.googleplay.Http.Url;
import com.HY.googleplay.R;
import com.HY.googleplay.Utils.GlideUtil;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by 杂兵 on 2017/7/28.
 */

public class DetileImgModule extends BaseModoule<ArrayList<String>> {
    @BindView(R.id.ll_screen_detile)
    LinearLayout ll_screen_detile;

    public DetileImgModule(Context context) {
        super(context);
    }

    @Override
    protected int getLayout() {
        return R.layout.detile_screen_module;
    }

    @Override
    public void bindData(final ArrayList<String> info) {


        for (int i = 0; i < info.size(); i++) {
            ImageView imageView = new ImageView(context);
            GlideUtil.loadImage(Url.ImagePrefix + info.get(i), imageView);

            int dp90 = context.getResources().getDimensionPixelSize(R.dimen.dp90);
            int dp150 = context.getResources().getDimensionPixelSize(R.dimen.dp150);
            int dp15 = context.getResources().getDimensionPixelSize(R.dimen.dp15);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(dp90, dp150);
            layoutParams.setMargins(dp15, dp15, dp15, dp15);
            layoutParams.leftMargin = (i == 0 ? 0 : dp15);
            imageView.setLayoutParams(layoutParams);


            imageView.setScaleType(ImageView.ScaleType.FIT_XY);


            final int finalI = i;
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(context, DetileImageActivity.class);
                    intent.putStringArrayListExtra("urlList", info);
                    intent.putExtra("position", finalI);
                    context.startActivity(intent);
                }
            });

            ll_screen_detile.addView(imageView);
        }
    }
}
