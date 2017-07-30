package com.HY.googleplay.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;


/**
 * 主要的adapter
 * Created by 杂兵 on 2017/7/21.
 */

public class MainpagerAdapter extends FragmentPagerAdapter {
    //标题集合
    private ArrayList<String> titleList = new ArrayList<>();
    //fragment集合
    private ArrayList<Fragment> fragmentList = new ArrayList<>();

    public MainpagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }

    public void addData(Fragment fragment, String name) {
        titleList.add(name);
        fragmentList.add(fragment);
    }
}
