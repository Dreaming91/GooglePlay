package com.HY.googleplay.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.HY.googleplay.Activity.DetileActivity;
import com.HY.googleplay.Adapter.MyRecycleAdapter;
import com.HY.googleplay.Base.BaseFragment;
import com.HY.googleplay.Bean.AppInfo;
import com.HY.googleplay.Bean.HomeBean;
import com.HY.googleplay.Http.Url;
import com.HY.googleplay.R;
import com.HY.googleplay.Utils.GsonUtil;
import com.scwang.smartrefresh.header.PhoenixHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by 杂兵 on 2017/7/21.
 */

public class HomeFragment extends BaseFragment {

    private ArrayList<AppInfo> dataList = new ArrayList<>();
    @BindView(R.id.home_rv)
    RecyclerView homeRv;
    @BindView(R.id.home_srfl)
    SmartRefreshLayout homeSrfl;
    private MyRecycleAdapter recycleAdapter;
    private ViewPager viewPager;


    public View getSuccessView() {
        View view = View.inflate(getContext(), R.layout.layout_home, null);
        ButterKnife.bind(this, view);
        initView();
//        setHeaderView();
        setPullLoadingAnimation();
        onFreshingPage();
        recycleAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {

                startActivity(new Intent(getContext(), DetileActivity.class).putExtra("packagename", dataList.get(position).packageName));
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        return view;
    }

    private void setHeaderView() {
        viewPager = (ViewPager) LayoutInflater.from(getContext()).inflate(R.layout.home_viewpager, homeRv, false);
    }

    private void initView() {
        homeRv.setLayoutManager(new LinearLayoutManager(getContext()));
        recycleAdapter = new MyRecycleAdapter(getContext(), R.layout.item_home_recycle, dataList);
        homeRv.setAdapter(recycleAdapter);

    }

    private void setPullLoadingAnimation() {
        homeSrfl.setRefreshHeader(new PhoenixHeader(getContext()));
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
                isfresh = true;
                loadNet();
            }
        });
    }

    private boolean isfresh = false;

    public void loadNet() {
        int start = isfresh ? 0 : dataList.size();
        OkHttpUtils.get()
                .url(Url.Home + start)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        loadingFrame.showErr();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        loadingFrame.showSuccess();
                        HomeBean homeBean = GsonUtil.parseJsonToBean(response, HomeBean.class);
//                        viewPager.setAdapter(new MyHeaderAdapter(homeBean.picture));
                        if (isfresh) {
                            isfresh = false;
                            dataList.clear();
                        }
                        dataList.addAll(homeBean.list);
                        recycleAdapter.notifyDataSetChanged();
                        homeSrfl.finishRefresh();
                        homeSrfl.finishLoadmore();
                    }
                });

    }


}
