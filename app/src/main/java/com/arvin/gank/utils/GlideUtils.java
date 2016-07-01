package com.arvin.gank.utils;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.DrawableRes;
import android.view.View;
import android.widget.ImageView;

import com.arvin.gank.R;
import com.arvin.gank.widget.GlideCircleTransform;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.SizeReadyCallback;

/**
 * Created by xiaoyi on 2016/7/1.
 */
public class GlideUtils {
    private static final String TAG = "GlideUtils";

    /**
     * glide加载图片
     *
     * @param view view
     * @param url  url
     */
    public static void display(ImageView view, String url) {
        display(view, url, R.mipmap.img_default_gray);
    }

    private static void display(final ImageView view, String url, @DrawableRes int defaultImg) {
        if (view == null) {
            return;
        }
        Context context = view.getContext();
        if (context instanceof Activity) {
            if (((Activity) context).isFinishing()) {
                return;
            }
        }
        Glide.with(context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(defaultImg)
                .crossFade()
                .centerCrop()
                .into(view)
                .getSize(new SizeReadyCallback() {
                    @Override
                    public void onSizeReady(int width, int height) {
                        if (view.isShown()) {
                            view.setVisibility(View.VISIBLE);
                        }
                    }
                });
    }

    public static void displayNative(final ImageView view, @DrawableRes int resId) {
        if (view == null) {
            return;
        }
        Context context = view.getContext();
        if (context instanceof Activity) {
            if (((Activity) context).isFinishing()) {
                return;
            }
        }

        Glide.with(context)
                .load(resId)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .centerCrop()
                .into(view)
                .getSize(new SizeReadyCallback() {
                    @Override
                    public void onSizeReady(int width, int height) {
                        if (view.isShown()) {
                            view.setVisibility(View.VISIBLE);
                        }
                    }
                });
    }

    public static void displayCircleHeader(ImageView view, @DrawableRes int res) {
        if (view == null) {
            return;
        }
        Context context = view.getContext();
        if (context instanceof Activity) {
            if (((Activity) context).isFinishing()) {
                return;
            }
        }
        Glide.with(context)
                .load(res)
                .centerCrop()
                .placeholder(R.mipmap.img_default_gray)
                .bitmapTransform(new GlideCircleTransform(context))
                .crossFade()
                .into(view);
    }
}
