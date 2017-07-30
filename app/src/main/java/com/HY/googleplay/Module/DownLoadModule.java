package com.HY.googleplay.Module;

import android.content.Context;
import android.widget.Button;
import android.widget.ProgressBar;

import com.HY.googleplay.Bean.AppInfo;
import com.HY.googleplay.Globle.HelloGoogle;
import com.HY.googleplay.Http.Url;
import com.HY.googleplay.R;
import com.HY.googleplay.Utils.AppInstallUtils;
import com.HY.googleplay.Utils.LogUtils;
import com.lxj.okhttpdownloader.download.DownloadEngine;
import com.lxj.okhttpdownloader.download.DownloadInfo;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 杂兵 on 2017/7/29.
 */

public class DownLoadModule extends BaseModoule<AppInfo> implements DownloadEngine.DownloadObserver {
    @BindView(R.id.pb_download_module)
    ProgressBar pbDownloadModule;
    @BindView(R.id.btn_download_module)
    Button btnDownloadModule;

    private AppInfo appInfo = null;
    private String taskid;

    public DownLoadModule(Context context) {
        super(context);
    }

    @Override
    protected int getLayout() {
        return R.layout.detile_download_module;
    }

    @Override
    public void bindData(AppInfo appInfo) {
        this.appInfo = appInfo;
        taskid = appInfo.id + "";
        DownloadEngine.create(context).addDownloadObserver(this, taskid);
        DownloadInfo downloadInfo = DownloadEngine.create(context).getDownloadInfo(taskid);

        if (downloadInfo == null) {
            btnDownloadModule.setText("下载");
        } else {
            downloadview(downloadInfo);
        }

    }

    private void downloadview(DownloadInfo downloadInfo) {
        switch (downloadInfo.state) {
            case DownloadEngine.STATE_NONE:
                btnDownloadModule.setText("下载");
                break;
            case DownloadEngine.STATE_DOWNLOADING:
                int progress = (int) (downloadInfo.currentLength * 100f / downloadInfo.size);
                pbDownloadModule.setProgress(progress);
                //给0可以设置背景为空
                btnDownloadModule.setBackgroundResource(0);
                btnDownloadModule.setText(progress + "%");
                break;
            case DownloadEngine.STATE_ERROR:
                btnDownloadModule.setText("重新下载");
                break;
            case DownloadEngine.STATE_FINISH:
                btnDownloadModule.setText("安装");
                btnDownloadModule.setBackgroundResource(R.drawable.selector_btn);
                break;
            case DownloadEngine.STATE_PAUSE:
                btnDownloadModule.setText("继续下载");
                int progress1 = (int) (downloadInfo.currentLength * 100f / downloadInfo.size);
                pbDownloadModule.setProgress(progress1);
                btnDownloadModule.setBackgroundResource(0);
                break;
            case DownloadEngine.STATE_WAITING:
                btnDownloadModule.setText("等待中");
                break;
        }
    }


    @OnClick(R.id.btn_download_module)
    public void onViewClicked() {
//        String taskid = appInfo.id+"";
        String path = HelloGoogle.downloadPakage + "/" + appInfo.name + ".apk";
        DownloadInfo downloadInfo = DownloadEngine.create(context).getDownloadInfo(taskid);

        if (downloadInfo == null) {
            String downloadurl = String.format(Url.Download, appInfo.downloadUrl);
            DownloadEngine.create(context).download(taskid, downloadurl, path);
        } else {
            if (downloadInfo.state == DownloadEngine.STATE_DOWNLOADING
                    || downloadInfo.state == DownloadEngine.STATE_WAITING) {
                LogUtils.e("downloading");
                DownloadEngine.create(context).pause(taskid);
            } else if (downloadInfo.state == DownloadEngine.STATE_ERROR || downloadInfo.state == DownloadEngine.STATE_PAUSE) {
                String breakUrl = String.format(Url.BreakDownload, appInfo.downloadUrl, downloadInfo.currentLength);
                DownloadEngine.create(context).download(taskid, breakUrl, path);
                LogUtils.e("jixu");
            } else if (downloadInfo.state == DownloadEngine.STATE_FINISH) {
                AppInstallUtils.Install(downloadInfo.path, context);
            }

        }
    }

    @Override
    public void onDownloadUpdate(DownloadInfo downloadInfo) {
        downloadview(downloadInfo);
    }

    public void removeobserver() {
        DownloadEngine.create(context).removeDownloadObserver(this, taskid);
    }
}
