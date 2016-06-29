package com.arvin.gank;

import android.app.Application;
import android.content.Context;

import com.anupcowkur.reservoir.Reservoir;
import com.arvin.gank.gank.GankApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import java.io.IOException;

/**
 * Created by arvin on 2016/6/23.
 */
public class App extends Application {
    private RefWatcher refWatcher;
    private static Context context;
    public Gson gson;


    public static final long ONE_KB = 1024L;
    public static final long ONE_MB = ONE_KB * 1024L;
    public static final long CACHE_DATA_MAX_SIZE = ONE_MB * 3L;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        refWatcher = LeakCanary.install(this);
        initGson();
        initReservoir();
        Logger.init();
    }

    private void initReservoir() {
        try {
            Reservoir.init(this, CACHE_DATA_MAX_SIZE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initGson() {
        gson = new GsonBuilder().setDateFormat(GankApi.GANK_DATA_FORMAT).create();
    }

    public static Context getContext() {
        return context;
    }

    public static RefWatcher getRefWatcher(Context context) {
        return ((App) context.getApplicationContext()).refWatcher;
    }
}
