package com.arvin.gank.gank;

import com.arvin.gank.App;
import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by xiaoyi on 2016/6/29.
 */
public class EasyGank {
    private volatile static EasyGank instance;
    private static final OkHttpClient.Builder builder = new OkHttpClient.Builder();


    private GankService gankService;

    public static EasyGank getInstance() {
        if (null == instance) {
            synchronized (EasyGank.class) {
                if (null == instance) {
                    instance = new EasyGank();
                }
            }
        }
        return instance;
    }

    private EasyGank() {
        OkHttpClient okhttpClient = new OkHttpClient.Builder().connectTimeout(7676, TimeUnit.MILLISECONDS)
                .build();
        /*if (App.log) {
            okhttpClient.interceptors().add(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Response response = chain.proceed(chain.request());
                    Logger.d(chain.request().url());
                    return response;
                }
            });
        }*/


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GankApi.BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(App.getInstance().gson))
                //.client(okhttpClient)
                .build();
        gankService = retrofit.create(GankService.class);
    }

    public GankService getGankService() {
        return gankService;
    }
}
