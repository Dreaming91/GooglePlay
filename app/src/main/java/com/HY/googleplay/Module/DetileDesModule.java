package com.HY.googleplay.Module;

import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Handler;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.HY.googleplay.Bean.AppInfo;
import com.HY.googleplay.R;

import butterknife.BindView;

/**
 * Created by 杂兵 on 2017/7/28.
 */

public class DetileDesModule extends BaseModoule<AppInfo> {
    @BindView(R.id.tv_detile_dec)
    TextView tvDetileDec;
    @BindView(R.id.tv_dec_author)
    TextView tvDecAuthor;
    @BindView(R.id.ll_dec_anim)
    LinearLayout llDecAnim;
    @BindView(R.id.ll_dec)
    LinearLayout llDec;
    @BindView(R.id.iv_dec)
    ImageView ivDec;
    private ScrollView scrollView;
    private int minheight;
    private int maxheight;
    private boolean isopen = false;
    private boolean finish = false;
    private ValueAnimator animator = null;
    private ViewGroup.LayoutParams layoutParams;

    public DetileDesModule(Context context) {
        super(context);
        llDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (finish) return;

                if (isopen) {
                    animator = ValueAnimator.ofInt(maxheight, minheight);
                } else {
                    animator = ValueAnimator.ofInt(minheight, maxheight);
                }
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        layoutParams = tvDetileDec.getLayoutParams();
                        layoutParams.height = (int) animation.getAnimatedValue();
                        tvDetileDec.setLayoutParams(layoutParams);
                        if (isopen) {
                            scrollView.smoothScrollBy(0, 100);
                        }

                    }
                });
                animator.setDuration(600).start();

                ViewCompat.animate(ivDec).rotationBy(180).setDuration(600).start();
                finish = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish = false;
                    }
                }, 600);

                isopen = !isopen;
            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.detile_des_module;
    }

    @Override
    public void bindData(AppInfo appInfo) {

        tvDecAuthor.setText(appInfo.author);
        tvDetileDec.setText(appInfo.des);
        llDecAnim.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                tvDetileDec.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                maxheight = tvDetileDec.getHeight();
                tvDetileDec.setMaxLines(5);
                tvDetileDec.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        tvDetileDec.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                        minheight = tvDetileDec.getHeight();
                        tvDetileDec.setMaxLines(Integer.MAX_VALUE);
                        layoutParams = tvDetileDec.getLayoutParams();
                        layoutParams.height = minheight;
                        tvDetileDec.setLayoutParams(layoutParams);
                    }
                });


            }
        });
    }

    public void getScrollView(ScrollView scrollView) {
        this.scrollView = scrollView;
    }
}
