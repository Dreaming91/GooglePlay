package com.HY.googleplay.View;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.HY.googleplay.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 杂兵 on 2017/7/21.
 */

public class LoadingFrame extends FrameLayout {

    @BindView(R.id.btn_reload)
    Button btnReload;
    private View loadingView;
    private View errView;
    private View successView;

    public LoadingFrame(Context context) {
        this(context, null);
    }

    public LoadingFrame(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingFrame(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        loadingView = View.inflate(getContext(), R.layout.page_loading, null);
        addView(loadingView);

        errView = View.inflate(getContext(), R.layout.page_error, null);
        ButterKnife.bind(this, errView);
        addView(errView);

        btnReload.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                        showLoading();
                        listener.ReloadData();
            }
        });
        hideView();

    }

    private void hideView() {
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            child.setVisibility(INVISIBLE);
        }
    }

    public void setSuccesView(View succes) {
        if (succes != null) {
            this.successView = succes;
            addView(successView);
        }
        hideView();
    }


    public void showSuccess() {
        hideView();
        if (successView != null) {
            successView.setVisibility(VISIBLE);
        }
    }

    public void showErr() {
        hideView();
        errView.setVisibility(VISIBLE);
    }

    public void showLoading() {
        hideView();
        loadingView.setVisibility(VISIBLE);
    }


    OnReloadDataListener listener;

    public void setOnReloadDataListener(OnReloadDataListener listener) {
        this.listener = listener;
    }

    public interface OnReloadDataListener {
        void ReloadData();
    }


}
