package com.arvin.gank.core;

import android.os.Bundle;
import android.support.annotation.Nullable;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by xiaoyi on 2016/6/28.
 */
public abstract class BusDrawerLayoutActivity extends BaseDrawerLayoutActivity {

    protected CompositeSubscription mCompositeSubscription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCompositeSubscription = null;
    }
}
