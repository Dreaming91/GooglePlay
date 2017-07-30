package com.HY.googleplay.Fragment;

import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.HY.googleplay.Adapter.MyRecycleAdapter;
import com.HY.googleplay.Base.BaseFragment;
import com.HY.googleplay.Bean.AppInfo;
import com.HY.googleplay.R;
import com.HY.googleplay.Utils.GsonUtil;
import com.google.gson.reflect.TypeToken;
import com.scwang.smartrefresh.header.StoreHouseHeader;
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

import static com.HY.googleplay.Http.Url.App;

/**
 *
 * Created by 杂兵 on 2017/7/21.
 */

public class AppFragment extends BaseFragment {


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

        onFreshPage();

        return view;
    }

    private void initView() {
        homeRv.setLayoutManager(new LinearLayoutManager(getContext()));
        recycleAdapter = new MyRecycleAdapter(getContext(), R.layout.item_home_recycle, dataList);
        homeRv.setAdapter(recycleAdapter);
    }

    private void setPullLoadingAnimation() {

        StoreHouseHeader storeHouseHeader = new StoreHouseHeader(getContext());
        storeHouseHeader.initWithString("MyHeartWillGoOn", 30);
        homeSrfl.setRefreshHeader(storeHouseHeader);
        homeSrfl.setPrimaryColors(Color.parseColor("#673AB7"));
        homeSrfl.setRefreshFooter(new BallPulseFooter(getContext()).setSpinnerStyle(SpinnerStyle.Scale));
    }

    private void onFreshPage() {
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
                .url(App + start)
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
                        List<AppInfo> appBean = (List<AppInfo>) GsonUtil.parseJsonToList(response, type);
                        if (isfresh) {
                            isfresh = false;
                            dataList.clear();
                        }
                        dataList.addAll(appBean);
                        recycleAdapter.notifyDataSetChanged();
                        homeSrfl.finishRefresh();
                        homeSrfl.finishLoadmore();
                    }
                });

    }


}
