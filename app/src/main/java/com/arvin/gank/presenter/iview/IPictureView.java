package com.arvin.gank.presenter.iview;

import android.net.Uri;

import com.arvin.gank.core.mvp.MvpView;

/**
 * Created by xiaoyi on 2016/7/8.
 */
public interface IPictureView extends MvpView {
    /**
     * 分享
     */
    void onShare(Uri uri);

    /**
     * 下载
     */
    void onDownloadSuccess(String path);
}
