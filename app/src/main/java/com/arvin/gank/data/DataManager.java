package com.arvin.gank.data;

import com.arvin.gank.bean.GankDaily;
import com.arvin.gank.bean.GankData;
import com.arvin.gank.presenter.MainPresenter;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by xiaoyi on 2016/6/28.
 */
public class DataManager {
    private static volatile DataManager mInstance;

    private DataManager() {
    }

    public static DataManager getInstance() {
        if (mInstance == null) {
            synchronized (DataManager.class) {
                if (mInstance == null) {
                    mInstance = new DataManager();
                }
            }
        }
        return mInstance;
    }

    /*public Observable<List<GankDaily>> getDailyDataByNetWork(MainPresenter.EasyDate currentDate){
        return Observable.just(currentDate)
                .flatMapIterable(new Func1<MainPresenter.EasyDate, Iterable<MainPresenter.EasyDate>>() {
                    @Override
                    public Iterable<MainPresenter.EasyDate> call(MainPresenter.EasyDate easyDate) {
                        return easyDate.getPastTime();
                    }
                }).flatMap(new Func1<MainPresenter.EasyDate, Observable<List<GankDaily>>>() {
                    @Override
                    public Observable<List<GankDaily>> call(MainPresenter.EasyDate easyDate) {
                        return null;
                    }
                });
                }).compose(null);
    }*/
}
