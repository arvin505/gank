package com.arvin.gank.views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.arvin.gank.R;
import com.arvin.gank.annotations.LayoutId;
import com.arvin.gank.core.BaseToolbarActivity;
import com.arvin.gank.utils.GlideUtils;
import com.arvin.gank.utils.IntentUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
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
public class PictureActivity extends BaseToolbarActivity {
    private static final String EXTRA_URL = "com.camnter.easygank.EXTRA_URL";
    private static final String EXTRA_TITLE = "com.camnter.easygank.EXTRA_TITLE";

    private static final String SHARED_ELEMENT_NAME = "PictureActivity";

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
        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(activity,view,"girl");

        ActivityCompat.startActivity(activity, intent, activityOptionsCompat.toBundle());
    }


    @Override
    protected void initListeners() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

        //ViewCompat.setTransitionName(pictureIv, SHARED_ELEMENT_NAME);
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
                if (pictureIv.isShown()){
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

}
