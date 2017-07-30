package com.HY.googleplay.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.HY.googleplay.Adapter.DetileScreenAdapter;

import java.util.ArrayList;

/**
 *  图片详情页
 * Created by 杂兵 on 2017/7/28.
 */
public class DetileImageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayList<String> urlList = getIntent().getStringArrayListExtra("urlList");
        int position = getIntent().getIntExtra("position", 0);
        ViewPager viewPager = new ViewPager(this);
        setContentView(viewPager);
        DetileScreenAdapter adapter = new DetileScreenAdapter(urlList);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(position);

        getActionBars();

    }

    private void getActionBars() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("详情图");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}
