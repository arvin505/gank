package com.arvin.gank.data;

import com.arvin.gank.bean.GankDaily;
import com.arvin.gank.model.impl.DailyModel;
import com.arvin.gank.presenter.MainPresenter;
import com.arvin.gank.utils.RxUtils;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * Created by xiaoyi on 2016/6/28.
 */
public class DataManager {
    private static volatile DataManager mInstance;

    private DailyModel dailyModel;


    private DataManager() {
        dailyModel = DailyModel.getInstance();
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

    public Observable<List<GankDaily>> getDailyDataByNetWork(MainPresenter.EasyDate currentDate) {
        return Observable.just(currentDate)
                .flatMapIterable(new Func1<MainPresenter.EasyDate, Iterable<MainPresenter.EasyDate>>() {
                    @Override
                    public Iterable<MainPresenter.EasyDate> call(MainPresenter.EasyDate easyDate) {
                        return easyDate.getPastTime();
                    }
                }).flatMap(new Func1<MainPresenter.EasyDate, Observable<GankDaily>>() {
                    @Override
                    public Observable<GankDaily> call(MainPresenter.EasyDate easyDate) {
                        return dailyModel.getDaily(easyDate.getYear(), easyDate.getMoth(), easyDate.getDay())
                                .filter(new Func1<GankDaily, Boolean>() {
                                    @Override
                                    public Boolean call(GankDaily gankDaily) {
                                        return gankDaily.results.androidData != null;
                                    }
                                });
                    }
                }).toSortedList(new Func2<GankDaily, GankDaily, Integer>() {
                    @Override
                    public Integer call(GankDaily gankDaily, GankDaily gankDaily2) {
                        return gankDaily2.results.androidData.get(0).publishedAt.compareTo(
                                gankDaily.results.androidData.get(0).publishedAt
                        );
                    }
                }).compose(RxUtils.<List<GankDaily>>applyIOToMainThreadSchedulers());

    }
}
