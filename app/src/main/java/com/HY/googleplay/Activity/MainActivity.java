package com.HY.googleplay.Activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.HY.googleplay.Adapter.MainpagerAdapter;
import com.HY.googleplay.Fragment.AppFragment;
import com.HY.googleplay.Fragment.CategryFragment;
import com.HY.googleplay.Fragment.GameFragment;
import com.HY.googleplay.Fragment.HomeFragment;
import com.HY.googleplay.Fragment.HotspotFragment;
import com.HY.googleplay.Fragment.RecommentFragment;
import com.HY.googleplay.Fragment.SubjectFragment;
import com.HY.googleplay.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *     主页面
 *     Created by 杂兵 on 2017/7/22.
 */

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tb_main)
    TabLayout tbMain;
    @BindView(R.id.vp_main)
    ViewPager vpMain;
    @BindView(R.id.ll_main)
    LinearLayout llMain;
    @BindView(R.id.drawerlayout)
    DrawerLayout drawerlayout;

    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setActionBar();
        initView();
    }

    //设置各种页面的adapter
    private void initView() {

        MainpagerAdapter mainpagerAdapter = new MainpagerAdapter(getSupportFragmentManager());
        mainpagerAdapter.addData(new HomeFragment(), "首页");
        mainpagerAdapter.addData(new AppFragment(), "应用");
        mainpagerAdapter.addData(new GameFragment(), "游戏");
        mainpagerAdapter.addData(new SubjectFragment(), "专题");
        mainpagerAdapter.addData(new RecommentFragment(), "推荐");
        mainpagerAdapter.addData(new HotspotFragment(), "热门");
        mainpagerAdapter.addData(new CategryFragment(), "分类");

        vpMain.setAdapter(mainpagerAdapter);
        tbMain.setupWithViewPager(vpMain);


    }

    //设置actionBar属性和汉堡版按钮
    private void setActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        //创建汉堡包按钮
        drawerToggle = new ActionBarDrawerToggle(this, drawerlayout, 0, 0);
        drawerToggle.syncState();
        drawerlayout.addDrawerListener(drawerToggle);
    }

    //系统提供的按钮监听
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        drawerToggle.onOptionsItemSelected(item);
        return super.onOptionsItemSelected(item);
    }



}
