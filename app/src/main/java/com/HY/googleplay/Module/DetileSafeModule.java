package com.HY.googleplay.Module;

import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Handler;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.HY.googleplay.Bean.SafeInfo;
import com.HY.googleplay.Http.Url;
import com.HY.googleplay.R;
import com.HY.googleplay.Utils.GlideUtil;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by 杂兵 on 2017/7/28.
 */

public class DetileSafeModule extends BaseModoule<ArrayList<SafeInfo>> {
    @BindView(R.id.iv_detile_safe_title1)
    ImageView ivDetileSafeTitle1;
    @BindView(R.id.iv_detile_safe_title2)
    ImageView ivDetileSafeTitle2;
    @BindView(R.id.iv_detile_safe_title3)
    ImageView ivDetileSafeTitle3;
    @BindView(R.id.iv_detile_safe_content1)
    ImageView ivDetileSafeContent1;
    @BindView(R.id.tv_detile_safe_content1)
    TextView tvDetileSafeContent1;
    @BindView(R.id.iv_detile_safe_content2)
    ImageView ivDetileSafeContent2;
    @BindView(R.id.tv_detile_safe_content2)
    TextView tvDetileSafeContent2;
    @BindView(R.id.iv_detile_safe_content3)
    ImageView ivDetileSafeContent3;
    @BindView(R.id.tv_detile_safe_content3)
    TextView tvDetileSafeContent3;
    @BindView(R.id.ll_detile_safe_content2)
    LinearLayout llDetileSafeContent2;
    @BindView(R.id.ll_detile_safe_content3)
    LinearLayout llDetileSafeContent3;
    @BindView(R.id.ll_safe_anim)
    LinearLayout llSafeAnim;
    @BindView(R.id.iv_safe_title4)
    ImageView ivSafeTitle4;
    @BindView(R.id.ll_safe_click)
    LinearLayout llSafeClick;


    private boolean isOpen = false;
    private boolean finish = false;
    private ValueAnimator animator = null;
    private int startPaddingTop;
    private int paddingTop;

    public DetileSafeModule(Context context) {
        super(context);

        llSafeClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (finish) return;
                if (isOpen) {
                    animator = ValueAnimator.ofInt(startPaddingTop, paddingTop);
                } else {
                    animator = ValueAnimator.ofInt(paddingTop, startPaddingTop);
                }
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        int animatedValue = (int) animation.getAnimatedValue();
                        llSafeAnim.setPadding(llSafeAnim.getPaddingLeft(), animatedValue, llSafeAnim.getPaddingRight(),
                                llSafeAnim.getPaddingBottom());
                    }
                });
                animator.setDuration(600).start();
                isOpen = !isOpen;
                ViewCompat.animate(ivSafeTitle4).rotationBy(180).setDuration(600).start();
                finish = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish = false;
                    }
                }, 600);
            }
        });

    }

    @Override
    protected int getLayout() {
        return R.layout.detile_safe_module;
    }

    @Override
    public void bindData(ArrayList<SafeInfo> safeInfos) {
        GlideUtil.loadImage(Url.ImagePrefix + safeInfos.get(0).safeUrl, ivDetileSafeTitle1);
        GlideUtil.loadImage(Url.ImagePrefix + safeInfos.get(0).safeDesUrl, ivDetileSafeContent1);
        tvDetileSafeContent1.setText(safeInfos.get(0).safeDes);


        if (safeInfos.size() > 1) {
            GlideUtil.loadImage(Url.ImagePrefix + safeInfos.get(1).safeUrl, ivDetileSafeTitle2);
            GlideUtil.loadImage(Url.ImagePrefix + safeInfos.get(1).safeDesUrl, ivDetileSafeContent2);
            tvDetileSafeContent2.setText(safeInfos.get(1).safeDes);
        } else {
            llDetileSafeContent2.setVisibility(View.GONE);
            ivDetileSafeTitle2.setVisibility(View.GONE);
        }

        if (safeInfos.size() > 2) {
            GlideUtil.loadImage(Url.ImagePrefix + safeInfos.get(2).safeUrl, ivDetileSafeTitle3);
            GlideUtil.loadImage(Url.ImagePrefix + safeInfos.get(2).safeDesUrl, ivDetileSafeContent3);
            tvDetileSafeContent3.setText(safeInfos.get(2).safeDes);
        } else {
            llDetileSafeContent3.setVisibility(View.GONE);
            ivDetileSafeTitle3.setVisibility(View.GONE);
        }

        llSafeAnim.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                llSafeAnim.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                startPaddingTop = llSafeAnim.getPaddingTop();
                paddingTop = -llSafeAnim.getHeight();
                llSafeAnim.setPadding(llSafeAnim.getPaddingLeft(), paddingTop, llSafeAnim.getPaddingRight(),
                        llSafeAnim.getPaddingBottom());

            }
        });

    }


}
