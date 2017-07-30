package com.HY.googleplay.Activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.VideoView;
import com.HY.googleplay.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 *    闪屏页面
 *    Created by 杂兵 on 2017/7/22.
 */
public class SpalashActivity extends AppCompatActivity {


    @BindView(R.id.vv_splash)
    VideoView vvSplash;
    @BindView(R.id.btn_splash)
    Button btnSplash;
    @BindView(R.id.activity_spalash)
    RelativeLayout activitySpalash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalash);
        ButterKnife.bind(this);

        initview();


        initviewSize();

    }

    private void initviewSize() {
        int widthPixels = getResources().getDisplayMetrics().widthPixels;
        int heightPixels = getResources().getDisplayMetrics().heightPixels;
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(widthPixels, heightPixels);
        vvSplash.setLayoutParams(layoutParams);

    }

    private void initview() {
        String path = "android.resource://" + getPackageName() + "/" + R.raw.kr36;
        vvSplash.setVideoPath(path);
        vvSplash.start();
        vvSplash.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                vvSplash.start();
            }
        });
    }

    @OnClick(R.id.btn_splash)
    public void onViewClicked() {
        startActivity(new Intent(this, MainActivity.class));
        finish();

    }

}
