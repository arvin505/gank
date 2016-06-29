package com.arvin.gank.utils;

import android.util.Log;

import com.anupcowkur.reservoir.Reservoir;
import com.anupcowkur.reservoir.ReservoirDeleteCallback;
import com.anupcowkur.reservoir.ReservoirGetCallback;
import com.anupcowkur.reservoir.ReservoirPutCallback;
import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.lang.reflect.Type;

import rx.Observable;

/**
 * Created by xiaoyi on 2016/6/29.
 */
public class ReservoirUtils {

    private final static String TAG = "ReservoirUtils";

    private volatile static ReservoirUtils instance;

    private ReservoirUtils() {
    }

    public static ReservoirUtils getInstance() {
        if (instance == null) {
            synchronized (ReservoirUtils.class) {
                if (instance == null) {
                    instance = new ReservoirUtils();
                }
            }
        }
        return instance;
    }

    public void put(final String key, final Object object) {
        if (null == object) return;
        Reservoir.putAsync(key, object, new ReservoirPutCallback() {
            @Override
            public void onSuccess() {
                Logger.i(key, object.getClass());
                Log.i(TAG, "Put success: key=" + key + " object=" + object.getClass());
            }

            @Override
            public void onFailure(Exception e) {
                e.printStackTrace();
            }
        });
    }

    public boolean contains(String key) {
        try {
            return Reservoir.contains(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void delete(String key) {
        if (contains(key)) {
            Reservoir.deleteAsync(key);
        }
    }

    public void refresh(final String key, final Object object) {
        if (contains(key)) {
            Reservoir.deleteAsync(key, new ReservoirDeleteCallback() {
                @Override
                public void onSuccess() {
                    put(key, object);
                }

                @Override
                public void onFailure(Exception e) {
                    e.printStackTrace();
                }
            });
        } else {
            put(key, object);
        }
    }

    public <T> Observable<T> get(Class<T> clazz) {
        String key = clazz.getSimpleName();
        return get(key, clazz);
    }

    private <T> Observable<T> get(String key, Class<T> clazz) {
        return Reservoir.getAsync(key, clazz);
    }

    public <T> void get(final String key, final Type typeOfT, final ReservoirGetCallback<T> callback) {
        Reservoir.getAsync(key, typeOfT, callback);
    }
}
