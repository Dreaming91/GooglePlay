package com.HY.googleplay.Fragment;

import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.HY.googleplay.Adapter.SubjectAdapter;
import com.HY.googleplay.Base.BaseFragment;
import com.HY.googleplay.Bean.Subject;
import com.HY.googleplay.Http.Url;
import com.HY.googleplay.R;
import com.HY.googleplay.Utils.GsonUtil;
import com.google.gson.reflect.TypeToken;
import com.scwang.smartrefresh.header.FunGameBattleCityHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by 杂兵 on 2017/7/21.
 */

public class SubjectFragment extends BaseFragment {

    private ArrayList<Subject> dataList = new ArrayList<>();
    @BindView(R.id.home_rv)
    RecyclerView homeRv;
    @BindView(R.id.home_srfl)
    SmartRefreshLayout homeSrfl;
    private SubjectAdapter subjectAdapter;

    public View getSuccessView() {
        View view = View.inflate(getContext(), R.layout.layout_home, null);
        ButterKnife.bind(this, view);
        subjectAdapter = new SubjectAdapter(getContext(), R.layout.item_subject_recycle, dataList);
        homeRv.setLayoutManager(new LinearLayoutManager(getContext()));
        homeRv.setAdapter(subjectAdapter);

        homeSrfl.setRefreshHeader(new FunGameBattleCityHeader(getContext()));
        homeSrfl.setPrimaryColors(Color.parseColor("#673AB7"));
        homeSrfl.setRefreshFooter(new BallPulseFooter(getContext()).setSpinnerStyle(SpinnerStyle.Scale));

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

        return view;
    }

    private boolean isfresh = false;

    public void loadNet() {
        int start = isfresh ? 0 : dataList.size();
        OkHttpUtils.get().
                url(Url.Subject + start).
                build().
                execute(new StringCallback() {


                    @Override
                    public void onError(Call call, Exception e, int id) {
                        loadingFrame.showErr();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        loadingFrame.showSuccess();
                        ArrayList<Subject> subjectbean = (ArrayList<Subject>) GsonUtil.parseJsonToList
                                (response, new TypeToken<List<Subject>>() {
                                }.getType());
                        if (isfresh) {
                            isfresh = false;
                            dataList.clear();
                        }
                        dataList.addAll(subjectbean);
                        subjectAdapter.notifyDataSetChanged();
                        homeSrfl.finishLoadmore();
                        homeSrfl.finishRefresh();
                    }
                });

    }


}
