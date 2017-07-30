package com.HY.googleplay.Activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.HY.googleplay.Bean.AppInfo;
import com.HY.googleplay.Http.Url;
import com.HY.googleplay.Module.DetileDesModule;
import com.HY.googleplay.Module.DetileImgModule;
import com.HY.googleplay.Module.DetileInfoModule;
import com.HY.googleplay.Module.DetileSafeModule;
import com.HY.googleplay.Module.DownLoadModule;
import com.HY.googleplay.R;
import com.HY.googleplay.Utils.GsonUtil;
import com.HY.googleplay.View.LoadingFrame;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * 程序详情页
 * Created by 杂兵 on 2017/7/28.
 */

public class DetileActivity extends AppCompatActivity {

    @BindView(R.id.ll_detile_main)
    LinearLayout llDetileMain;
    @BindView(R.id.sv_detile)
    ScrollView svDetile;
    @BindView(R.id.fl_detile_main)
    FrameLayout flDetileMain;
    private LoadingFrame loadingFrame;
    private String packagename;
    private DetileInfoModule detileInfoModule;
    private DetileSafeModule detileSafeModule;
    private DetileImgModule detileImgModule;
    private DetileDesModule detileDesModule;
    private DownLoadModule downLoadModule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        packagename = getIntent().getStringExtra("packagename");
        loadingFrame = new LoadingFrame(getApplicationContext());
        setContentView(loadingFrame);



        loadingFrame.showLoading();
        loadingFrame.setSuccesView(initview());
        setActionBars();
        loadData();


    }

    private void setActionBars() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.app_detail);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    private void loadData() {
        OkHttpUtils.get().url(String.format(Url.Detail, packagename)).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                loadingFrame.showErr();
            }

            @Override
            public void onResponse(String response, int id) {
                loadingFrame.showSuccess();
                AppInfo info = GsonUtil.parseJsonToBean(response, AppInfo.class);

                updateUI(info);
            }
        });
    }

    private void updateUI(AppInfo info) {

        detileInfoModule.bindData(info);

        detileSafeModule.bindData(info.safe);

        detileImgModule.bindData(info.screen);

        detileDesModule.bindData(info);

        downLoadModule.bindData(info);
    }


    private View initview() {
        View view = View.inflate(this, R.layout.activity_detile, null);
        ButterKnife.bind(this, view);

        detileInfoModule = new DetileInfoModule(this);
        llDetileMain.addView(detileInfoModule.view);

        detileSafeModule = new DetileSafeModule(this);
        llDetileMain.addView(detileSafeModule.view);

        detileImgModule = new DetileImgModule(this);
        llDetileMain.addView(detileImgModule.view);

        detileDesModule = new DetileDesModule(this);
        llDetileMain.addView(detileDesModule.view);
        detileDesModule.getScrollView(svDetile);

        downLoadModule = new DownLoadModule(this);
        flDetileMain.addView(downLoadModule.view);

        return view;

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        downLoadModule.removeobserver();
    }
}
