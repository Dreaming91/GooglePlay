package com.HY.googleplay.Base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.HY.googleplay.View.LoadingFrame;

/**
 * fragment的基类
 * Created by 杂兵 on 2017/7/21.
 */

public abstract class BaseFragment extends Fragment implements LoadingFrame.OnReloadDataListener {


    public  LoadingFrame loadingFrame;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (loadingFrame == null) {
            loadingFrame = new LoadingFrame(getContext());
            loadingFrame.setSuccesView(getSuccessView());
            loadingFrame.showLoading();
            loadNet();
            loadingFrame.setOnReloadDataListener(this);
        }
        return loadingFrame;
    }


    public abstract View getSuccessView();

    public abstract void loadNet();

    @Override
    public void ReloadData() {
        loadNet();
    }

}
