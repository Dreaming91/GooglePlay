package com.HY.googleplay.Fragment;

import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.HY.googleplay.Adapter.MyRecycleAdapter;
import com.HY.googleplay.Base.BaseFragment;
import com.HY.googleplay.Bean.AppInfo;
import com.HY.googleplay.Http.Url;
import com.HY.googleplay.R;
import com.HY.googleplay.Utils.GsonUtil;
import com.google.gson.reflect.TypeToken;
import com.scwang.smartrefresh.header.DeliveryHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by 杂兵 on 2017/7/21.
 */

public class GameFragment extends BaseFragment {


    private ArrayList<AppInfo> dataList = new ArrayList<>();
    @BindView(R.id.home_rv)
    RecyclerView homeRv;
    @BindView(R.id.home_srfl)
    SmartRefreshLayout homeSrfl;
    private MyRecycleAdapter recycleAdapter;


    public View getSuccessView() {
        View view = View.inflate(getContext(), R.layout.layout_home, null);
        ButterKnife.bind(this, view);
        initView();

        setPullLoadingAnimation();

        onFreshingPage();

        return view;
    }

    private void initView() {
        homeRv.setLayoutManager(new LinearLayoutManager(getContext()));
        recycleAdapter = new MyRecycleAdapter(getContext(), R.layout.item_home_recycle, dataList);
        homeRv.setAdapter(recycleAdapter);
    }

    private void setPullLoadingAnimation() {
        homeSrfl.setRefreshHeader(new DeliveryHeader(getContext()));
        homeSrfl.setPrimaryColors(Color.parseColor("#673AB7"));
        homeSrfl.setRefreshFooter(new BallPulseFooter(getContext()).setSpinnerStyle(SpinnerStyle.Scale));
    }

    private void onFreshingPage() {
        homeSrfl.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                loadNet();

            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                loadNet();
            }
        });
    }

    private boolean isfresh = false;

    public void loadNet() {
        int start = isfresh ? 0 : dataList.size();
        OkHttpUtils.get()
                .url(Url.Game + start)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        loadingFrame.showErr();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        loadingFrame.showSuccess();
                        Type type = new TypeToken<List<AppInfo>>() {
                        }.getType();
                        List<AppInfo> gameBean = (List<AppInfo>) GsonUtil.parseJsonToList(response, type);

                        if (isfresh) {
                            isfresh = false;
                            dataList.clear();
                        }
                        dataList.addAll(gameBean);
                        recycleAdapter.notifyDataSetChanged();
                        homeSrfl.finishRefresh();
                        homeSrfl.finishLoadmore();
                    }
                });

    }

}
