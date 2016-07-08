package com.arvin.gank.presenter;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;

import com.arvin.gank.core.mvp.BasePresenter;
import com.arvin.gank.presenter.iview.IPictureView;
import com.arvin.gank.utils.DeviceUtils;
import com.arvin.gank.utils.RxUtils;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by xiaoyi on 2016/7/8.
 */
public class PicturePresenter extends BasePresenter<IPictureView> {
    public Observable<String> getSavePictureObservable(
            final GlideBitmapDrawable glideBitmapDrawable, final Context context, final Application application) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    String dirPath = DeviceUtils.createAPPFolder("ArvinGank妹子", application);
                    File downloadFile = new File(new File(dirPath), UUID.randomUUID().toString().replace("-", "") + ".jpg");
                    if (!downloadFile.exists()) {
                        File parent = downloadFile.getParentFile();
                        if (!parent.exists()) parent.mkdirs();
                    }
                    FileOutputStream output = new FileOutputStream(downloadFile);
                    glideBitmapDrawable.getBitmap().compress(Bitmap.CompressFormat.JPEG, 100, output);
                    output.close();
                    Uri uri = Uri.fromFile(downloadFile);
                    Intent scannerIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri);
                    context.sendBroadcast(scannerIntent);
                    subscriber.onNext(downloadFile.getPath());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).compose(RxUtils.<String>applyIOToMainThreadSchedulers());
    }

    public void downloadPicture(GlideBitmapDrawable glideBitmapDrawable, Context context, Application application) {
        mCompositeSubscription.add(getSavePictureObservable(glideBitmapDrawable, context, application)
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        mCompositeSubscription.remove(this);
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (getMvpView() != null) {
                            getMvpView().onFailure(e);
                        }
                        mCompositeSubscription.remove(this);
                    }

                    @Override
                    public void onNext(String s) {
                        if (getMvpView() != null)
                            getMvpView().onDownloadSuccess(s);
                    }
                }));
    }

    public void sharePicture(GlideBitmapDrawable glideBitmapDrawable, Context context, Application application) {
        mCompositeSubscription.add(getSavePictureObservable(glideBitmapDrawable, context, application)
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        mCompositeSubscription.remove(this);
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (getMvpView() != null) getMvpView().onFailure(e);
                    }

                    @Override
                    public void onNext(String s) {
                        if (getMvpView() != null) getMvpView().onShare(Uri.parse("file://" + s));
                    }
                }));
    }
}
