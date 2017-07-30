package com.HY.googleplay.Module;

import android.content.Context;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by 杂兵 on 2017/7/28.
 */

public abstract class BaseModoule <SB>{

    protected Context context;
    public  View view;

    public BaseModoule(Context context) {
        this.context = context;
        view = View.inflate(context, getLayout(), null);
        ButterKnife.bind(this, view);
    }

    protected abstract int getLayout();

    public  abstract void bindData(SB sb);


}
