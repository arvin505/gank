package com.arvin.gank;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by arvin on 2016/6/23.
 */
public class App extends Application {
    private RefWatcher refWatcher;
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        refWatcher = LeakCanary.install(this);
    }

    public static Context getContext() {
        return context;
    }

    public static RefWatcher getRefWatcher(Context context) {
        return ((App) context.getApplicationContext()).refWatcher;
    }
}
