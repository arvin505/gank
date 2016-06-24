package com.arvin.gank.core;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.arvin.gank.annotations.LayoutId;
import com.arvin.gank.utils.ToastUtils;

import butterknife.ButterKnife;

/**
 * Created by xiaoyi on 2016/6/24.
 */
public abstract class BaseAppCompatActivity extends AppCompatActivity {
    protected String TAG = getClass().getSimpleName();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int resId = getClass().getAnnotation(LayoutId.class).value();
        setContentView(resId);
        ButterKnife.bind(this);
        initToolbar(savedInstanceState);
        initViews(savedInstanceState);
        initData();
        initListeners();
    }

    protected abstract void initToolbar(Bundle savedInstanceState);

    protected abstract void initListeners();

    protected abstract void initViews(Bundle savedInstanceState);

    protected abstract void initData();

    protected <T extends View> T findView(int resId) {
        return (T) findViewById(resId);
    }

    protected <T extends View> T findView(View group, int resId) {
        return (T) group.findViewById(resId);
    }

    @UiThread
    protected void showToast(int resId) {
        ToastUtils.show(resId);
    }

    @UiThread
    protected void showToast(String msg) {
        ToastUtils.show(msg);
    }


}
