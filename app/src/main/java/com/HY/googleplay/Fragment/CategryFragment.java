package com.HY.googleplay.Fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.HY.googleplay.Adapter.CategryContentAdapter;
import com.HY.googleplay.Adapter.CategryTitleAdapter;
import com.HY.googleplay.Base.BaseFragment;
import com.HY.googleplay.Bean.CategryBean;
import com.HY.googleplay.Http.Url;
import com.HY.googleplay.Utils.GsonUtil;
import com.google.gson.reflect.TypeToken;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by 杂兵 on 2017/7/21.
 */

public class CategryFragment extends BaseFragment {

    private ArrayList<Object> categryList = new ArrayList<>();
    private RecyclerView recyclerView;

    public void loadNet() {
        OkHttpUtils.get().url(Url.Category).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                loadingFrame.showErr();
            }

            @Override
            public void onResponse(String response, int id) {
                loadingFrame.showSuccess();
                ArrayList<CategryBean> list = (ArrayList<CategryBean>) GsonUtil.parseJsonToList(response,
                        new TypeToken<List<CategryBean>>() {
                        }.getType());

                for (CategryBean categryBean : list) {
                    categryList.add(categryBean.title);
                    categryList.addAll(categryBean.infos);
                }

                MultiItemTypeAdapter<Object> itemTypeAdapter = new MultiItemTypeAdapter<>(getContext(), categryList);
                itemTypeAdapter.addItemViewDelegate(new CategryTitleAdapter());
                itemTypeAdapter.addItemViewDelegate(new CategryContentAdapter());
                recyclerView.setAdapter(itemTypeAdapter);

            }
        });

    }


    public View getSuccessView() {
        recyclerView = new RecyclerView(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return recyclerView;
    }


}
