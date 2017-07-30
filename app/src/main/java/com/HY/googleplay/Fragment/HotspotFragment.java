package com.HY.googleplay.Fragment;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.view.View;
import android.widget.TextView;

import com.HY.googleplay.Base.BaseFragment;
import com.HY.googleplay.Http.Url;
import com.HY.googleplay.Utils.DrableUtils;
import com.HY.googleplay.Utils.GsonUtil;
import com.HY.googleplay.Utils.ToastUtils;
import com.HY.googleplay.View.FlowLayout;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by 杂兵 on 2017/7/21.
 */

public class HotspotFragment extends BaseFragment {


    private FlowLayout flowLayout;
    private NestedScrollView scrollView;


    public void loadNet() {
        OkHttpUtils.get().url(Url.Hot).build().execute(new StringCallback() {


            @Override
            public void onError(Call call, Exception e, int id) {
                loadingFrame.showErr();
            }

            @Override
            public void onResponse(String response, int id) {
                loadingFrame.showSuccess();
                ArrayList<String> HotList = (ArrayList<String>) GsonUtil.parseJsonToList(response,
                        new TypeToken<List<String>>() {
                        }.getType());
                for (int i = 0; i < HotList.size(); i++) {
                    final TextView textView = new TextView(getContext());
                    textView.setText(HotList.get(i));
                    textView.setTextColor(Color.WHITE);

                    textView.setTextSize(16);
                    textView.setPadding(20, 15, 20, 15);

                    GradientDrawable normal = DrableUtils.createGD(20);
                    GradientDrawable press = DrableUtils.createGD(20);
                    textView.setBackgroundDrawable(DrableUtils.createSD(press, normal));
                    textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ToastUtils.showToast(getContext(), textView.getText().toString());


                            ViewCompat.animate(textView).scaleY(1.5f).scaleX(1.5f)
                                    .setDuration(800)
                                    .start();
                            new Handler() {
                            }.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    ViewCompat.animate(textView).scaleY(1.0f).scaleX(1.0f)
                                            .setDuration(800)
                                            .start();
                                }
                            }, 800);

                        }
                    });
                    flowLayout.addView(textView);
                }
            }
        });

    }


    public View getSuccessView() {
        scrollView = new NestedScrollView(getContext());
        flowLayout = new FlowLayout(getContext());
        flowLayout.setPadding(30, 30, 30, 30);
        flowLayout.setHorizontalsize(30);
        flowLayout.setVerticalsize(30);
        flowLayout.setfullscreen(true);
        scrollView.addView(flowLayout);
        return scrollView;
    }


}
