package com.HY.googleplay.Fragment;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.HY.googleplay.Base.BaseFragment;
import com.HY.googleplay.Http.Url;
import com.HY.googleplay.Utils.GsonUtil;
import com.HY.googleplay.Utils.ToastUtils;
import com.HY.googleplay.View.randomlayout.StellarMap;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import okhttp3.Call;

/**
 * Created by 杂兵 on 2017/7/21.
 */

public class RecommentFragment extends BaseFragment {


    ArrayList<String> RecommentList = null;
    private StellarMap stellarMap;

    public void loadNet() {
        OkHttpUtils.get().url(Url.Recommend).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                loadingFrame.showErr();
            }

            @Override
            public void onResponse(String response, int id) {
                loadingFrame.showSuccess();

                RecommentList = (ArrayList<String>) GsonUtil.parseJsonToList(response,
                        new TypeToken<List<String>>() {
                        }.getType());

                InStellAdapter stellAdapter = new InStellAdapter();
                stellarMap.setAdapter(stellAdapter);
                stellarMap.setGroup(0, true);
                stellarMap.setRegularity(3, 4);
            }
        });

    }


    public View getSuccessView() {

        stellarMap = new StellarMap(getContext());

        stellarMap.setInnerPadding(15, 15, 15, 15);

        return stellarMap;
    }


    class InStellAdapter implements StellarMap.Adapter {
        @Override
        public int getGroupCount() {
            return RecommentList.size()/11;
        }

        @Override
        public int getCount(int group) {
            return 11;
        }

        @Override
        public View getView(int group, int position, View convertView) {
             final TextView textView = new TextView(getContext());


            textView.setText(RecommentList.get(group * getCount(group) + position));
            int red = new Random().nextInt(150) + 50;
            int green = new Random().nextInt(150) + 50;
            int blue = new Random().nextInt(150) + 50;
            textView.setTextColor(Color.rgb(red, green, blue));

            int size = new Random().nextInt(15) + 12;
            textView.setTextSize(size);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtils.showToast(getContext(), textView.getText().toString());
                }
            });
            return textView;
        }

        //没啥卵用
        @Override
        public int getNextGroupOnPan(int group, float degree) {
            return 0;
        }

        @Override
        public int getNextGroupOnZoom(int group, boolean isZoomIn) {
            //单目运算符不能放在联合运算中 （group++）%getGroupCount得不到值
            group++;
            return (group) % getGroupCount();
        }
    }
}
