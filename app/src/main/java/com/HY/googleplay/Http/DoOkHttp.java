package com.HY.googleplay.Http;

import android.os.Handler;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 *
 * 自己封装okhttp
 *
 * Created by 杂兵 on 2017/7/22.
 */

public class DoOkHttp {
    private Handler handler = new Handler();
    private static DoOkHttp doOkHttp = new DoOkHttp();
    private final OkHttpClient build;

    private DoOkHttp() {
        build = new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .build();
    }

    public static DoOkHttp creat() {
        return doOkHttp;
    }


    public void getdata(String url, final HttpCallback hc) {
        Request request = new Request.Builder()
                .url(url)
                .get()

                .build();

        build.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        hc.faillure();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        hc.succsess();
                    }
                });
            }
        });
    }


    public interface HttpCallback {
        void succsess();

        void faillure();
    }

}
