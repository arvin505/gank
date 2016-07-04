package com.arvin.gank.utils;

import android.content.Context;
import android.content.res.Resources;
import android.widget.Toast;

import com.arvin.gank.App;

/**
 * Created by xiaoyi on 2016/6/24.
 */
public class ToastUtils {

    private static Context mContext = App.getContext();
    private static Resources mResources = mContext.getResources();
    private volatile static Toast mToast;

    public static void show(String msg, int duration) {
        if (mToast == null) {
            synchronized (ToastUtils.class) {
                if (mToast == null) {
                    mToast = Toast.makeText(mContext, msg, duration);
                }
            }
        }
        mToast.setDuration(duration);
        mToast.setText(msg);
        mToast.show();
    }

    public static void show(int resId, int duration) {
        String msg = mResources.getString(resId);
        show(msg, duration);
    }


    public static void show(String msg) {
        show(msg, Toast.LENGTH_SHORT);
    }

    public static void show(int resId) {
        show(mResources.getString(resId), Toast.LENGTH_SHORT);
    }

}
