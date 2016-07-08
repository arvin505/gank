package com.arvin.gank.views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.arvin.gank.App;
import com.arvin.gank.R;
import com.arvin.gank.annotations.LayoutId;
import com.arvin.gank.core.BaseToolbarActivity;
import com.arvin.gank.presenter.PicturePresenter;
import com.arvin.gank.presenter.iview.IPictureView;
import com.arvin.gank.utils.DeviceUtils;
import com.arvin.gank.utils.IntentUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.prefill.PreFillType;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;

import butterknife.BindView;

/**
 * Created by xiaoyi on 2016/7/7.
 */
@LayoutId(R.layout.activity_picture)
public class PictureActivity extends BaseToolbarActivity implements View.OnLongClickListener, IPictureView {
    private static final String EXTRA_URL = "com.camnter.easygank.EXTRA_URL";
    private static final String EXTRA_TITLE = "com.camnter.easygank.EXTRA_TITLE";

    private static final String SHARED_ELEMENT_NAME = "PictureActivity";

    private PicturePresenter presenter;

    @BindView(R.id.picture_iv)
    ImageView pictureIv;

    private GlideBitmapDrawable glideBitmapDrawable;

    public static void startActivity(Context context, String url, String title) {
        context.startActivity(createIntent(context, url, title));
    }

    private static Intent createIntent(Context context, String url, String title) {
        Intent intent = new Intent(context, PictureActivity.class);
        intent.putExtra(EXTRA_URL, url);
        intent.putExtra(EXTRA_TITLE, title);
        return intent;
    }


    public static void startActivityByActivityOptionsCompat(Activity activity, String url, String title, View view) {
        Intent intent = createIntent(activity, url, title);
        /*ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeScaleUpAnimation(
                view,
                view.getWidth() / 2,
                view.getHeight() / 2,
                view.getWidth(),
                view.getHeight()
        );*/
        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, view, "girl");

        ActivityCompat.startActivity(activity, intent, activityOptionsCompat.toBundle());
    }


    @Override
    protected void initListeners() {
        pictureIv.setOnLongClickListener(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

        //ViewCompat.setTransitionName(pictureIv, SHARED_ELEMENT_NAME);
        presenter = new PicturePresenter();
        presenter.attachView(this);
    }

    @Override
    protected void initData() {
        showBack();
        setTitle(getUrlTitle());
        Glide.with(this)
                .load(getUrl())
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        glideBitmapDrawable = (GlideBitmapDrawable) resource;
                        return false;
                    }
                })
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .into(pictureIv)
                .getSize(new SizeReadyCallback() {
                    @Override
                    public void onSizeReady(int width, int height) {
                        if (pictureIv.isShown()) {
                            pictureIv.setVisibility(View.VISIBLE);
                        }
                    }
                });
    }

    private String getUrlTitle() {
        return IntentUtils.getStringExtra(this.getIntent(), EXTRA_TITLE);
    }

    private String getUrl() {
        return IntentUtils.getStringExtra(this.getIntent(), EXTRA_URL);
    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_picture, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_picture_download:
                download();
                return true;
            case R.id.menu_picture_copy:
                DeviceUtils.copy2Clipboard(this, getUrl());
                Snackbar.make(pictureIv, "复制成功", Snackbar.LENGTH_SHORT).show();
                return true;
            case R.id.menu_picture_share:
                download();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void download() {
        if (glideBitmapDrawable != null) {
            presenter.downloadPicture(glideBitmapDrawable, this, App.getInstance());
        } else {
            Snackbar.make(pictureIv, "图片加载中，请稍等", Snackbar.LENGTH_LONG).show();
        }
    }

    @Override
    public void onShare(Uri uri) {

    }

    @Override
    public void onDownloadSuccess(String path) {
        showToast(path);
    }

    @Override
    public void onFailure(Throwable e) {
        Snackbar.make(pictureIv, "网络错误", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        if (glideBitmapDrawable != null) {
            glideBitmapDrawable.setCallback(null);
            glideBitmapDrawable = null;
        }
        presenter.detachView();
        super.onDestroy();
    }
}
