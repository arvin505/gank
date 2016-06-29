package com.arvin.gank.model.impl;

import com.arvin.gank.bean.GankDaily;
import com.arvin.gank.model.IDailyModel;

import rx.Observable;

/**
 * Created by xiaoyi on 2016/6/29.
 */
public class DailyModel implements IDailyModel {

    private static final DailyModel ourInstance = new DailyModel();

    @Override
    public Observable<GankDaily> getDaily(int year, int month, int day) {
        return Ea
    }

    private DailyModel() {
    }

    public DailyModel getInstance() {
        return ourInstance;
    }

}
